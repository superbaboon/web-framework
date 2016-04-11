package com.superbaboon.web.framework.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * execute before the actual api process
 *
 * Created by junjiewu on 16/4/5.
 */
public interface ApiExecutePreProcessor {

    void preProcess(API api, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

}
