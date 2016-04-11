package com.superbaboon.web.framework.core;

import javax.servlet.http.HttpServletRequest;

/**
 * For api implementation to get HttpServletRequest object
 *
 * Created by junjiewu on 16/4/5.
 */
public interface HttpServletRequestAware {

    void setRequest(HttpServletRequest request);

}
