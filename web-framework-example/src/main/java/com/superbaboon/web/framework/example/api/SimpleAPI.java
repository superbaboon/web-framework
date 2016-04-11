package com.superbaboon.web.framework.example.api;

import com.superbaboon.web.framework.core.API;
import com.superbaboon.web.framework.core.URL;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by junjiewu on 16/4/6.
 */
@Service
@Scope("prototype")
@URL(url = "/example/simple")
public class SimpleAPI implements API {

    private String foo;

    public Object execute() {
        return "hello " + foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }
}
