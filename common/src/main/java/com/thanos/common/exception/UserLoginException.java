package com.thanos.common.exception;

/**
 * Created by wangjialong on 6/11/18.
 */
public class UserLoginException extends RuntimeException {

    public String code = "1100";

    public String getCode() {
        return code;
    }

    public static class LoginAuthException extends UserLoginException {

        public LoginAuthException() {
            code = "1101";
        }

    }
}
