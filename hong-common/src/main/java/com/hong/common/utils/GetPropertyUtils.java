package com.hong.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <br>properties属性文件读取工具类，默认读取config.properties</br>
 */
public class GetPropertyUtils {

    public static final String CONFIG_ENV_VARIABLE = "config_env";

    public static final String DEFAULT_ENV = "dev";

    private ResourceBundle resourceBundle;

    public GetPropertyUtils(){
        this("config");
    }

    /**
     * @param propertiesHolder property文件名称
     */
    public GetPropertyUtils(String propertiesHolder){

        resourceBundle = ResourceBundle.getBundle(getEnv() + "/" + propertiesHolder, Locale.getDefault());
    }

    public String getEnv() {
        String env = System.getenv(CONFIG_ENV_VARIABLE);
        return StringUtils.isBlank(env) ? DEFAULT_ENV : env;
    }

    public String getLabel(String key) {
        String label;
        try {
            label = new String(resourceBundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return label;
    }
}
