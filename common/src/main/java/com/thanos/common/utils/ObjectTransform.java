package com.thanos.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangjialong on 6/13/18.
 */
public class ObjectTransform {

    public static Map<Class<?>, Field[]> cacheFields = new HashMap();

    public static Map<String, Object> object2Map(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap();

        Class<?> clazz = obj.getClass();
        Field[] fields = cacheFields.get(clazz);
        if (fields == null ) {
            fields = clazz.getDeclaredFields();
        }
        for (Field field: fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }
}
