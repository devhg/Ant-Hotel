package com.anthotel.face.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.anthotel.common.base.GlobalConstant;
import com.anthotel.common.base.ResultKit;
import com.anthotel.common.enums.GlobalCodeEnum;
import com.anthotel.face.base.Result;
import com.anthotel.face.base.Results;
import com.anthotel.face.dto.*;
import com.anthotel.face.enums.ErrorCodeEnum;
import com.anthotel.face.service.FaceEngineService;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FaceSimilar;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;


@Component
public class FaceVerification {

    public final static Logger logger = LoggerFactory.getLogger(FaceVerification.class);


    @Autowired
    FaceEngineService faceEngineService;


    public ResultKit<UserFaceInfo> getFaceFeature(byte[] videoImage, byte[] idCardImage) {
        ResultKit<UserFaceInfo> resultKit = new ResultKit<>();
        try {
            // 视频流照片信息
            ImageInfo videoImageInfo = ImageFactory.getRGBData(videoImage);
            // 身份证照片信息
            ImageInfo idCardImageImageInfo = ImageFactory.getRGBData(idCardImage);

            //人脸特征获取
            byte[] videoFeatureData = faceEngineService.extractFaceFeature(videoImageInfo);
            byte[] idCardFeatureData = faceEngineService.extractFaceFeature(idCardImageImageInfo);
            if (idCardFeatureData == null || videoFeatureData == null) {
                return null;
            }

            //特征比对
            FaceFeature peopleFeature = new FaceFeature();
            peopleFeature.setFeatureData(videoFeatureData);
            FaceFeature idCardFeature = new FaceFeature();
            idCardFeature.setFeatureData(idCardFeatureData);

            FaceSimilar faceSimilar = faceEngineService.comparePeopleIdCard(peopleFeature, idCardFeature);

            System.out.println("相似度：" + faceSimilar.getScore());

            File file1 = new File("D:\\Code\\IdeaProjects\\ArcSoftFaceDemo\\1.jpg");
            File file2 = new File("D:\\Code\\IdeaProjects\\ArcSoftFaceDemo\\2.jpg");
            FileOutputStream fos1 = new FileOutputStream(file1);
            FileOutputStream fos2 = new FileOutputStream(file2);
            fos1.write(videoImage);
            fos2.write(idCardImage);
            fos2.close();
            fos1.close();

            if (faceSimilar.getScore() > GlobalConstant.face_value) {

                UserFaceInfo userFaceInfo = new UserFaceInfo();
                userFaceInfo.setFaceFeature(idCardFeatureData);
                userFaceInfo.setFaceId(RandomUtil.randomString(10));

                resultKit.setCode(GlobalCodeEnum.IDCARD_VERIFICATION_SUCCESS.code());
                resultKit.setMessage(GlobalCodeEnum.IDCARD_VERIFICATION_SUCCESS.desc() + "正确率：" + String.valueOf(faceSimilar.getScore()));
                resultKit.setData(userFaceInfo);
            } else {
                resultKit.setCode(GlobalCodeEnum.IDCARD_VERIFICATION_ERROR.code());
                resultKit.setMessage(GlobalCodeEnum.IDCARD_VERIFICATION_ERROR.desc() + "正确率：" + String.valueOf(faceSimilar.getScore()));
            }
            logger.info("getFaceFeature:" + "success");
            return resultKit;
        } catch (Exception e) {
            logger.error("", e);
        }
        return resultKit;
    }

    /*
    人脸识别
     */
    public Result<FaceSearchResDto> faceSearch(byte[] decode, Integer groupId) throws Exception {

        if (groupId == null) {
            return Results.newFailedResult("groupId is null");
        }

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


    /*
    认证对比
     */
    public Result<FaceSearchResDto> faceVer(byte[] decode, Integer groupId) throws Exception {

        if (groupId == null) {
            return Results.newFailedResult("groupId is null");
        }

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


    public List<FaceInfo> detectFaces(String image) throws IOException {
        byte[] decode = Base64.decode(image);
        InputStream inputStream = new ByteArrayInputStream(decode);
        ImageInfo imageInfo = ImageFactory.getRGBData(inputStream);

        if (inputStream != null) {
            inputStream.close();
        }
        List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);

        return faceInfoList;
    }

}
