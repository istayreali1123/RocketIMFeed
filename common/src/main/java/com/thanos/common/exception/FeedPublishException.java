package com.thanos.common.exception;

/**
 * Created by wangjialong on 6/14/18.
 */
public class FeedPublishException extends RuntimeException {

    public String code= "1200";

    public String getCode() {
        return code;
    }

    public static class feedStorageException extends FeedPublishException {

        public feedStorageException() {
            code = "1201";
        }
    }
}
