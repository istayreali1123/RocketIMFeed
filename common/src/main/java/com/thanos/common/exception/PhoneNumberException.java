package com.thanos.common.exception;

/**
 * Created by wangjialong on 6/3/18.
 */
public class PhoneNumberException extends UserRegisterException {

    public static class VerifyCodeException extends PhoneNumberException {

    }

    public static class FormatException extends PhoneNumberException {

    }
}
