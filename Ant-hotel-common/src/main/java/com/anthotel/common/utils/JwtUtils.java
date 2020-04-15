package com.anthotel.common.utils;

import com.anthotel.common.base.CheckResult;
import com.anthotel.common.base.TokenConstant;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

//import sun.tools.jstat.Token;

/**
 * @ author qxqzx
 * @ Description:JWT工具类JSON WEB TOKEN
 */
public class JwtUtils {
    /**
     * 签发JWT
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        //加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
//        Map<String, Object> claims = new HashMap<String, Object>();
//        claims.put("id", 1);
//        claims.put("username", 2);
//        claims.put("password", 3);
        //密钥
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(id)
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                //.setClaims(claims)
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject) //主题
                .setIssuer(TokenConstant.ISSUER)//签发者
                .setAudience(TokenConstant.AUD)//受众(接收方)
                .setIssuedAt(now)//签发时间
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, secretKey)
                .setHeaderParam("typ", "JWT");

        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);//设置过期时间
        }
        return builder.compact();
    }

    /**
     * 验证JWT
     *
     * @param jwt
     * @return
     */
    public static CheckResult validateJWT(String jwt) {
        //生成返回类
        CheckResult checkResult = new CheckResult();
        Claims claims = null;
        try {
            claims = parseJWT(jwt);
            if (claims == null) {
                checkResult.setSuccess(false);
                checkResult.setErrorCode(TokenConstant.JWT_ERRCODE_FAIL);
            } else if (!claims.getAudience().equals(TokenConstant.AUD)) {
                checkResult.setSuccess(false);
                checkResult.setErrorCode(TokenConstant.JWT_ERRCODE_FAIL);
            } else if (!claims.getIssuer().equals(TokenConstant.ISSUER)) {
                checkResult.setSuccess(false);
                checkResult.setErrorCode(TokenConstant.JWT_ERRCODE_FAIL);
            } else {
                checkResult.setSuccess(true);
                checkResult.setClaims(claims);
            }

        } catch (ExpiredJwtException e) {
            //JWT过期
            checkResult.setSuccess(false);
            checkResult.setErrorCode(TokenConstant.JWT_ERRCODE_EXPIRE);
        } catch (SignatureException e) {
            //签名错误
            checkResult.setSuccess(false);
            checkResult.setErrorCode(TokenConstant.JWT_ERRCODE_FAIL);
        } catch (Exception e) {
            checkResult.setSuccess(false);
            checkResult.setErrorCode(TokenConstant.JWT_ERRCODE_FAIL);
        }

        return checkResult;
    }

    /**
     * 构建密钥
     *
     * @return SecretKey
     */
    public static SecretKey generalKey() {
        //密钥解密
        byte[] encodedKey = Base64.decode(TokenConstant.JWT_SECRET);
        //生成密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();

    }
}
