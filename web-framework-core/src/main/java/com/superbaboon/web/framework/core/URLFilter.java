package com.superbaboon.web.framework.core;

import javax.servlet.http.HttpServletRequest;

/**
 * to tell the framework which api should be blocked
 *
 * Created by junjiewu on 16/4/5.
 */
public interface URLFilter {

    /**
     * name of urlFilter
     *
     * @return
     */
    String getName();

    /**
     * tell if current http request should be blocked
     *
     * @param httpServletRequest
     * @return true -> should be blocked
     */
    boolean needFilter(HttpServletRequest httpServletRequest);
}
