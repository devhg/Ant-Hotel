package com.anthotel.common.utils;

import com.anthotel.common.base.CheckResult;
import com.anthotel.common.base.ResultKit;

/**
 * @ author Devhui
 * @ Description:Token验证
 */
public class MainUtils {

    public static ResultKit checkToken(String token) {
        //进行验证
        if (token != null) {
            CheckResult result = JwtUtils.validateJWT(token);
            if (result.isSuccess()) {
                ResultKit<CheckResult> resultKit = new ResultKit<>();
                resultKit.setCode(ResultCode.SUCCESS.code());
                resultKit.setMessage("成功获取信息");
                resultKit.setData(result);
                return resultKit;
            } else {
                ResultKit<String> resultKit = new ResultKit<>();
                resultKit.setCode(ResultCode.UNAUTHORIZED.code());
                resultKit.setMessage("错误:Token验证失败,或许是授权过期,或许是Token错误");
                resultKit.setData("没有权限进行操作");
                return resultKit;
            }

        } else {
            ResultKit<String> resultKit = new ResultKit<>();
            resultKit.setCode(ResultCode.FAIL.code());
            resultKit.setMessage("错误:没有检测到Token");
            resultKit.setData("没有权限进行操作");
            return resultKit;
        }
    }

}
