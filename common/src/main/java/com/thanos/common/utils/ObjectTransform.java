package com.thanos.common.utils;

import com.thanos.common.annotations.ToMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            ToMap annotation = field.getAnnotation(ToMap.class);
            if (field.getType().equals(List.class)) {
                List<Map<String, Object>> listMap = new ArrayList();
                List<Object> list = (List<Object>) field.get(obj);
                for (Object o1 : list) {
                    listMap.add(ObjectTransform.object2Map(o1));
                }
                map.put(field.getName(), listMap);
            } else {
                map.put(field.getName(), field.get(obj));
            }
        }
        return map;
    }
}
