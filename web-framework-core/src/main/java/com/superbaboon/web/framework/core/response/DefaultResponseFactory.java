package com.superbaboon.web.framework.core.response;

import com.superbaboon.web.framework.core.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by junjiewu on 16/4/11.
 */
public class DefaultResponseFactory {

    private static final String jsonpCallBackKey = "callback";

    private static final String jsonpFormatType = "jsonp";

    public static Response createResponse(HttpServletRequest httpServletRequest) {
        String formatType = getFormatKey(httpServletRequest);
        if (formatType == null) {
            return defaultResponse();
        }

        // json输出
        if (formatType.equals("json")) {
            return new JsonResponse();
        }

        // jsonp输出
        String callback = httpServletRequest.getParameter(jsonpCallBackKey);
        if (formatType.equals(jsonpFormatType) && callback != null) {
            return new JsonpResponse(callback);
        }

        return defaultResponse();
    }

    private static String getFormatKey(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String requestURI = request.getRequestURI();
        int index = requestURI.indexOf('.');
        if (index == -1) {
            return null;
        }
        return requestURI.substring(index+1);
    }

    private static Response defaultResponse() {
        return new JsonResponse();
    }

}
