package com.superbaboon.web.framework.core;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Autowired http request params to api properties
 *
 * Created by junjiewu on 16/4/6.
 */
public class HttpServletParamWrapProcessor implements ApiExecutePreProcessor {

    public void preProcess(API api, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            BeanUtils.populate(api, httpServletRequest.getParameterMap());
        } catch (Exception e) {
            throw new MessageAwareException(new ErrorCode(API.INVALID_PARAM_CODE, "param invalid"));
        }
    }

}
