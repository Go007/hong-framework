package com.hong.common.utils;

import java.util.Objects;

/**
 * <br>TODO(描述该类的作用)</br>
 *
 */
public class ObjectUtils {
    /**
     * 多参数非空或者非null校验,所有参数都不能为空或者null
     *
     * @param values
     * @return
     */
    public static boolean areNotEmptyOrNull(Object... values) {
        boolean result = true;
        if (values != null && values.length != 0) {
            Object[] params = values;
            int length = values.length;

            Object value;
            for (int i = 0; i < length; ++i) {
                value = params[i];
                if (value instanceof String) {
                    result &= !isEmpty((String) value);
                }else {
                    result &= Objects.nonNull(value);
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * 多参数非空或者非null校验,只要有参数不为空或null
     * @param values
     * @return
     */
    public static boolean anyNotEmptyOrNull(Object... values) {
        boolean result = true;
        if (values != null && values.length != 0) {
            Object[] params = values;
            int length = values.length;

            Object value;
            for (int i = 0; i < length; ++i) {
                value = params[i];
                if (value instanceof String) {
                    result |= !isEmpty((String) value);
                }else {
                    result |= Objects.nonNull(value);
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    public static boolean isEmpty(String value) {
        int strLen;
        if (value != null && (strLen = value.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(value.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }
}
