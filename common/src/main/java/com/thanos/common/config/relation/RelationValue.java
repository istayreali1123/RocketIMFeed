package com.thanos.common.config.relation;

/**
 * Created by wangjialong on 6/21/18.
 */
public enum RelationValue {

    SINGLE_LEFT_RELATION('0'),

    SINGLE_RIGHT_RELATION('1'),

    DOUBLE_RELATION('2');


    RelationValue(char value) {
        this.value = value;
    }

    private final char value;

    public char getValue() {
        return value;
    }
}
