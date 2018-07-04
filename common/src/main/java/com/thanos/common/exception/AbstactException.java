package com.thanos.common.exception;

/**
 * Created by wangjialong on 6/28/18.
 */
public class AbstactException extends RuntimeException {

    public String code = "-1";

    public String getCode() {
        return code;
    }
}
