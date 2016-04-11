package com.superbaboon.web.framework.core.response;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * Created by junjiewu on 16/4/11.
 */
public class JsonFormatter {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static String format(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    public static <T> T converse(String content, Class<T> cls) {
        try {
            return objectMapper.readValue(content, cls);
        } catch (Exception e) {
            return null;
        }
    }

}
