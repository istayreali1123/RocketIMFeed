package com.thanos.common.cache;

import com.thanos.common.annotations.*;
import com.thanos.common.pojo.AbstractMapper;
import com.thanos.common.pojo.UserMapper;
import com.thanos.common.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangjialong on 7/7/18.
 */
@Component
public abstract class ServiceCache<T extends AbstractMapper> {

    public static Method cacheMethod;

    public List<T> getData(List<String> ids, Class<T > dataType) {
        //1. get data from cache
        List<String> refreshIds = new ArrayList();
        Stream<String> userStream = ids.stream();
        List<T> dataByCache = userStream.map((key)->{
            Object value = BaseCache.get(key);
            if (value == null) {
                refreshIds.add(key);
            }
            return dataType.cast(value);
        }).filter((item)->item!=null).collect(Collectors.toList());

        if (refreshIds.isEmpty()) {
            return dataByCache;
        }

        //2. refresh the missing data into cache
        if (cacheMethod == null ) {
            try {
                cacheMethod = this.getClass().getDeclaredMethod("refreshData");
            } catch (NoSuchMethodException e) {
            }
        }
        GenerateCache annotation = cacheMethod.getAnnotation(GenerateCache.class);
        String dataSource = annotation.dataSource();
        String strMethod  = annotation.refreshMethod();
        List<T> dataInDB = new ArrayList();

        try {
            Class<?> dataClass = Class.forName(dataSource);
            Method refreshMethod = dataClass.getDeclaredMethod(strMethod, List.class);
            dataInDB = (List<T>) refreshMethod.invoke(null, refreshIds);

            for (T data: dataInDB ) {
                BaseCache.set(data.getIndentityId(), data, 30);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stream<T> streamDataByCache = dataByCache.stream();
        Stream<T> streamDataByDB    = dataInDB.stream();
        Stream<T> finalStream = Stream.concat(streamDataByCache, streamDataByDB);
        return finalStream.collect(Collectors.toList());
    }

    public abstract void refreshData();

}
