package com.anthotel.common.base;

/**
 * @Author: Devhui
 * @Date: 2020/3/7 21:32
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */
public class GlobalConstant {
    //密钥
    //原始码 sdut_jsjxy%softwarelab_9#305_design%by_qxqzx&20191101  md5加密
    public static final String JWT_SECRET = "721d7a1fd082fb4518a76f96327651ff";
    //签发者
    public static final String ISSUER = "superAdmin";
    //接收者
    public static final String AUD = "user";
    /****************************************************************/
    //JWT过期错误
    public static final Integer JWT_ERRCODE_EXPIRE = 85201;
    //JWT验证失败
    public static final Integer JWT_ERRCODE_FAIL = 85202;
    /****************************************************************/
    //请求头
    public static final String HEADER_TOKEN = "Authorization";
    // 人脸识别阈值
    public static final float face_value = 0.85f;
}
