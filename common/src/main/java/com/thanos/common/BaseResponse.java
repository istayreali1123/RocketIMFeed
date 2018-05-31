package com.thanos.common;

/**
 * Created by wangjialong on 5/31/18.
 */
public class BaseResponse {

    public void setMessage(String test) {

    }

    public static class ResponseBody <T> {

        private String code = "0";

        private String message = "";

        private T data;

        public void setCode(String code) {
            this.code = code;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }
}
