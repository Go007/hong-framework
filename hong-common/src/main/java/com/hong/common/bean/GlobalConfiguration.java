package com.hong.common.bean;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

/**
 * <br>通用配置类</br>
 *
 */
@Configuration
public class GlobalConfiguration {

    public static final Object DELIMITER = "-";

    public static final String NO_PARAM_KEY = "NO_PARAM";
    public static final String NULL_PARAM_KEY = "NULL_PARAM";

    /**
     * key 有两种生成方式，第一种是直接指定，这种方式一般用于某些定制化的缓存当中，
     * 通用返回接口的缓存得要用第二种方式，也就是通过键生成器来生成缓存的 key。
     * Spring Cache中提供了默认的 Key 生成器 org.springframework.cache.interceptor.SimpleKeyGenerator
     * 来生成 key，但是这个 key 是不会将函数名组合在 key 中，也是有缺陷，所以我们需要自定义一个 keyGenerator
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder key = new StringBuilder();
                //先将类的全限定名和方法名拼装在 key 中
                key.append(method.getDeclaringClass().getName()).append(".").append(method.getName()).append(":");
                if (params.length == 0) {
                    return key.append(NO_PARAM_KEY).toString();
                }
                //通过遍历参数,将参数也拼装在 key 中,保证每次获取key 的唯一性
                for (Object param : params) {
                    if (param == null) {
                        key.append(NULL_PARAM_KEY);
                    } else if (ClassUtils.isPrimitiveArray(param.getClass())) {
                        int length = Array.getLength(param);
                        for (int i = 0; i < length; i++) {
                            key.append(Array.get(param, i));
                            key.append(',');
                        }
                    } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
                        key.append(param);
                    } else {
                        key.append(param.hashCode());   //如果是map 或 model 类型
                    }
                    key.append(DELIMITER);
                }
                return  key.deleteCharAt(key.length() - 1).toString();
            }
        };
    }
}
