package com.superbaboon.web.framework.example.api;

import com.superbaboon.web.framework.core.API;
import com.superbaboon.web.framework.core.HttpServletRequestAware;
import com.superbaboon.web.framework.core.URL;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by junjiewu on 16/4/6.
 */
@Service
@Scope("prototype")
@URL(url = "/example/httpservletrequest")
public class HttpServletRequestAwareAPI implements API, HttpServletRequestAware {

    private String foo;

    private HttpServletRequest httpServletRequest;

    public Object execute() {
        return "address=" + httpServletRequest.getRemoteAddr() + "; foo=" + foo;
    }

    public void setRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }
}
