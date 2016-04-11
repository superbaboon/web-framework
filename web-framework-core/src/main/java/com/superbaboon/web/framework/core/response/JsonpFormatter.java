package com.superbaboon.web.framework.core.response;

/**
 * Created by junjiewu on 16/4/11.
 */
public class JsonpFormatter {

    private static String template = "%s(%s)";

    public static String format(String callback, String content) {
        return String.format(template, callback, content);
    }

}
