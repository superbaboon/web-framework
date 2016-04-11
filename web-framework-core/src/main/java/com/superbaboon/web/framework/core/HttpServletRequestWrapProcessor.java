package com.superbaboon.web.framework.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Autowired http request params to api properties
 *
 * Created by junjiewu on 16/4/6.
 */
public class HttpServletRequestWrapProcessor implements ApiExecutePreProcessor  {

    public void preProcess(API api, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (!(api instanceof HttpServletRequestAware)) {
            return;
        }
        ((HttpServletRequestAware) api).setRequest(httpServletRequest);
    }

}
