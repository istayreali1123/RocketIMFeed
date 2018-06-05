package com.thanos.dbgate.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class BaseDTO {

    private static Logger logger = Logger.getLogger(BaseDTO.class.getName());


    @Override
    public String toString() {
        //对数据对象tostring进行覆写描述
        Map<String, String> stringMap = new HashMap<String, String>();
        Field[] fields = getClass().getFields();
        //use reflect
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
            }catch (IllegalAccessException exception) {

            }
            if (null != value) {
                stringMap.put(field.getName().toString(), value.toString());
            }
        }
        return super.toString();
    }
}
