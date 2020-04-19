package com.anthotel.common.base;

import com.anthotel.common.enums.GlobalCodeEnum;
import lombok.Data;

/**
 * @Author: Devhui
 * @Date: 2020/3/7 21:29
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Data
public class ResultKit<T> {
    private Integer code; // 响应码
    private String message; // 响应消息
    private T data;

    public static <T> ResultKit<T> newSuccessResult(T data) {
        return newResult(data, "success", 200);
    }

    public static <T> ResultKit<T> newFailedResult(String message) {
        return newResult(null, message, -10000);
    }

    public static <T> ResultKit<T> newFailedResult(Integer code, String message) {
        return newResult(null, message, code);
    }

    public static <T> ResultKit<T> newFailedResult(GlobalCodeEnum errorCodeEnum) {
        return newResult(null, errorCodeEnum.desc(), errorCodeEnum.code());
    }

    public static <T> ResultKit<T> newResult(T data, String message, Integer code) {
        ResultKit result = new ResultKit();
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
