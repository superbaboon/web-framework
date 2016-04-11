package com.superbaboon.web.framework.core;

/**
 * To be implemented for actual business logic
 *
 * Field Values of api will be injected by the framework
 * according to the HttpServletRequest's params
 *
 * If what to get params from the HttpServletRequest object,
 * the actual implementation can implement the HttpServletRequestAware interface,
 * framework will inject for you.
 *
 * If what to get other value from the HttpServletRequest,
 * it can be achieved like this:
 * 1.define xxAware interface
 * 2.provide xxApiPreExecutor to extract data and inject into the actual api
 *
 * For example:
 * assume the url = http://domain/path?param1=value1&param2=value2
 *
 * @URL(url = "/path")
 * Class ActualAPI implements API {
 *
 *     private String param1;
 *
 *     private String param2;
 *
 *     @override
 *     public Object execute() {
 *         return param1 + param2; // replace by actual business logic
 *     }
 *
 *     public void setParam1(String param1) {
 *         this.param1 = param1;
 *     }
 *
 *     public void setParam2(String param2) {
 *         this.param2 = param2
 *     }
 * }
 *
 * Extra: provide some common error codes
 * Notice: should care threadsafe by yourself
 *
 * Created by junjiewu on 16/4/5.
 */
public interface API {

    int SERVER_ERROR_CODE = 500;

    int SUCCESS_ERROR_CODE = 200;

    int INVALID_PARAM_CODE = 403;

    int UNLOGIN_CODE = 401;

    /**
     * implemented for actual business logic
     *
     * @return
     */
    Object execute();

}
