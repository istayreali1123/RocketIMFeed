package com.thanos.common.exception;

/**
 * Created by wangjialong on 6/3/18.
 */
public class UserRegisterException extends RuntimeException {

    public String code = "1000";

    public String getCode() {
        return code;
    }

    public static class PhoneAlreadyUsedException extends UserRegisterException {

        public PhoneAlreadyUsedException() {
            code = "1001";
        }
    }

    public static class UserIdAllocException extends UserRegisterException {

        public UserIdAllocException() {
            code = "1002";
        }
    }
}
