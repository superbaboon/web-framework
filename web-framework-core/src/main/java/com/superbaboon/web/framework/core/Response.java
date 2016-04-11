package com.superbaboon.web.framework.core;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Define the lifecycle of response
 *
 * Created by junjiewu on 16/4/5.
 */
public interface Response {

    /**
     * initialize
     *
     * @param httpServletResponse
     * @throws IOException
     */
    void init(HttpServletResponse httpServletResponse) throws IOException;

    /**
     * triggered when business logic execute success
     *
     * @param data
     */
    void doSuccess(Object data);

    /**
     * triggered when business logic execute failed
     * @param code
     * @param msg
     */
    void doError(int code, String msg);

    /**
     * do some clean up operation
     */
    void destroy();

}
