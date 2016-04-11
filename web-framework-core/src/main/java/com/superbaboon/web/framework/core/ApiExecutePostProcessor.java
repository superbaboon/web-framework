package com.superbaboon.web.framework.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * execute after api process
 *
 * Created by junjiewu on 16/4/5.
 */
public interface ApiExecutePostProcessor {

    void postProcess(API api, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

}
