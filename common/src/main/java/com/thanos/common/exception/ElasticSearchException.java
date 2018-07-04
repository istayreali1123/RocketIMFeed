package com.thanos.common.exception;

/**
 * Created by wangjialong on 6/28/18.
 */
public class ElasticSearchException extends AbstactException {

    public String code = "1400";

    public static class ElasticSearchQueryException extends ElasticSearchException {

        public ElasticSearchQueryException() {
            code = "1401";
        }
    }
}
