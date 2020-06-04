package com.anthotel.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.anthotel.admin.mapper.OrderMapper;
import com.anthotel.common.base.ResultKit;
import com.anthotel.face.base.Result;
import com.anthotel.face.base.Results;
import com.anthotel.face.controller.FaceVerification;
import com.anthotel.face.dto.*;
import com.anthotel.face.enums.ErrorCodeEnum;
import com.anthotel.face.service.FaceEngineService;
import com.anthotel.web.dto.FormData;
import com.anthotel.web.mapper.FaceInfoMapper;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * @Author: Devhui
 * @Date: 2020/3/7 12:17
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Controller
@Api(tags = "人证识别入住相关接口")
public class MainController {
    public final static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private FaceVerification faceVerification;

    @Autowired
    private FaceEngineService faceEngineService;

    @Autowired
    private FaceInfoMapper faceInfoMapper;

    @Autowired
    private OrderMapper orderMapper;


    @GetMapping("welcome")
    @ApiOperation("主页")
    public String index(@RequestParam(value = "orderId", required = true) String orderId) {
        System.out.println("orderId = " + orderId);
        return "welcome";
    }

    @GetMapping("pay")
    @ApiOperation("人脸识别支付")
    public String pay(@RequestParam("orderId") String orderId) {
        System.out.println("orderId = " + orderId);
        return "pay";
    }

    @GetMapping("test")
    @ResponseBody
    public String test() {
        return "pay";
    }

    /*
    人脸添加
     */
    @PostMapping("faceAdd")
    @ResponseBody
    @ApiOperation("人脸识别接口")
    public ResultKit faceAdd(@RequestBody FormData formData) {
        System.out.println(formData);
        try {
            if (formData.getIdImage() == null) {
                return ResultKit.newFailedResult("身份证照片错误");
            }
            if (formData.getVideoImage() == null) {
                return ResultKit.newFailedResult("摄像头识别错误");
            }
            byte[] videoImage = Base64.decode(base64Process(formData.getVideoImage()));
            byte[] idCardImage = Base64.decode(base64Process(formData.getIdImage()));

            ResultKit<UserFaceInfo> result = faceVerification.getFaceFeature(videoImage, idCardImage);

            if (result.getData() != null) {
                UserFaceInfo userFaceInfo = result.getData();
                System.out.println("userFaceInfo = " + userFaceInfo);
                //人脸特征插入到数据库
                userFaceInfo.setName(formData.getName());
                userFaceInfo.setPhoneNumber(formData.getPhone());
                userFaceInfo.setUserId(SecureUtil.md5(formData.getUserId()));
                System.out.println(userFaceInfo);
                int i = faceInfoMapper.insertUserFaceInfo(userFaceInfo);
                orderMapper.updateStatus(formData.getOrderId());
                return ResultKit.newSuccessResult(result.getMessage());
            }
            logger.info("faceAdd:" + formData.getName());
            return ResultKit.newFailedResult(result.getCode(), result.getMessage());
        } catch (Exception e) {
            logger.error("", e);
        }
        return ResultKit.newFailedResult("识别认证错误，请重试");
    }

    /*
    人脸识别
     */
    @RequestMapping(value = "/faceSearch", method = RequestMethod.POST)
    @ResponseBody
    public Result<FaceSearchResDto> faceSearch(String file, Integer groupId) throws Exception {

        if (groupId == null) {
            return Results.newFailedResult("groupId is null");
        }
        byte[] decode = Base64.decode(base64Process(file));
        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);


        //人脸特征获取
        byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
        if (bytes == null) {
            return Results.newFailedResult(ErrorCodeEnum.NO_FACE_DETECTED);
        }
        //人脸比对，获取比对结果
        List<FaceUserInfo> userFaceInfoList = faceEngineService.compareFaceFeature(bytes, groupId);

        if (CollectionUtil.isNotEmpty(userFaceInfoList)) {
            FaceUserInfo faceUserInfo = userFaceInfoList.get(0);
            FaceSearchResDto faceSearchResDto = new FaceSearchResDto();
            BeanUtil.copyProperties(faceUserInfo, faceSearchResDto);
            List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
            if (CollectionUtil.isNotEmpty(processInfoList)) {
                //人脸检测
                List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
                int left = faceInfoList.get(0).getRect().getLeft();
                int top = faceInfoList.get(0).getRect().getTop();
                int width = faceInfoList.get(0).getRect().getRight() - left;
                int height = faceInfoList.get(0).getRect().getBottom() - top;

                Graphics2D graphics2D = bufImage.createGraphics();
                graphics2D.setColor(Color.RED);//红色
                BasicStroke stroke = new BasicStroke(5f);
                graphics2D.setStroke(stroke);
                graphics2D.drawRect(left, top, width, height);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufImage, "jpg", outputStream);
                byte[] bytes1 = outputStream.toByteArray();
                faceSearchResDto.setImage("data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
                faceSearchResDto.setAge(processInfoList.get(0).getAge());
                faceSearchResDto.setGender(processInfoList.get(0).getGender().equals(1) ? "女" : "男");

            }

            return Results.newSuccessResult(faceSearchResDto);
        }
        return Results.newFailedResult(ErrorCodeEnum.FACE_DOES_NOT_MATCH);
    }

    private String base64Process(String base64Str) {
        if (!StringUtils.isEmpty(base64Str)) {
            String photoBase64 = base64Str.substring(0, 30).toLowerCase();
            int indexOf = photoBase64.indexOf("base64,");
            if (indexOf > 0) {
                base64Str = base64Str.substring(indexOf + 7);
            }

            return base64Str;
        } else {
            return "";
        }
    }
}


