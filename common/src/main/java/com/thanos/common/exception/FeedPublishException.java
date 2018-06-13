package com.thanos.common.exception;

/**
 * Created by wangjialong on 6/14/18.
 */
public class FeedPublishException extends RuntimeException {

    public String code= "1200";

    public static class feddStorageException extends FeedPublishException {

        public feddStorageException() {
            code = "1201";
        }
    }
}
