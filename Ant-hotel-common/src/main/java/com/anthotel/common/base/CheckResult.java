package com.anthotel.common.base;

import io.jsonwebtoken.Claims;
import lombok.Data;

/**
 * jwt验证结果
 */
@Data
public class CheckResult {
    private boolean success;
    private int errorCode;
    private Claims claims;
}
