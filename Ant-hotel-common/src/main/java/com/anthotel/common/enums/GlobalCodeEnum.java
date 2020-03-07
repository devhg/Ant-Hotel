package com.anthotel.common.enums;

/**
 * @Author: Devhui
 * @Date: 2020/3/7 21:45
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */
public enum GlobalCodeEnum {
    UNKNOWN(1,"未知错误"),
    IDCARD_VERIFICATION_SUCCESS(6550, "认证验证成功"),
    IDCARD_VERIFICATION_ERROR(6551, "人证验证失败");


    private Integer code;
    private String description;

    GlobalCodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer code() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String desc() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static GlobalCodeEnum getDescriptionByCode(Integer code) {
        for (GlobalCodeEnum errorCodeEnum : GlobalCodeEnum.values()) {
            if (code.equals(errorCodeEnum.code())) {
                return errorCodeEnum;
            }
        }
        return GlobalCodeEnum.UNKNOWN;
    }

}
