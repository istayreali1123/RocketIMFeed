package com.thanos.common.exception;

/**
 * Created by wangjialong on 6/21/18.
 */
public class UserRelationException extends RuntimeException {

    public String code = "1300";

    public static class UserAlreadyFollowException extends UserRelationException {

        public UserAlreadyFollowException() {
            code = "1301";
        }
    }
}
