package com.superbaboon.web.framework.example;

import com.superbaboon.web.framework.core.*;
import com.superbaboon.web.framework.core.response.DefaultResponseFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by junjiewu on 16/4/6.
 */
public class MainFilter extends AbstractWebApiFilter {

    private ApplicationContext applicationContext;

    // api name --> api bean
    private Map<String, String> apiBeanNamesMap = new HashMap<String, String>();

    protected Response findMatchedResponse(HttpServletRequest httpServletRequest) {
        return DefaultResponseFactory.createResponse(httpServletRequest);
    }

    protected API findMatchedAPI(HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        return doFindMatchedAPI(requestURI);
    }

    private API doFindMatchedAPI(String requestURI) {
        String apiUrl = removeSuffix(requestURI);
        String beanName = apiBeanNamesMap.get(apiUrl);
        if(StringUtils.isEmpty(beanName)){
            return null;
        }
        Object bean = applicationContext.getBean(beanName);
        if (bean == null) {
            return null;
        }
        return (API) bean;
    }

    private String removeSuffix(String url) {
        int index = url.lastIndexOf('.');
        if (index == -1) {
            return url;
        }
        return url.substring(0, index);
    }

    @Override
    protected void doInit(FilterConfig filterConfig) {
        ServletContext servletContext = filterConfig.getServletContext();
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        Assert.notNull(applicationContext);

        buildRelationBetweenURLAndBeanName();

        // init processor list before api execute
        addApiExecutePreProcessor(new HttpServletParamWrapProcessor());
        addApiExecutePreProcessor(new HttpServletRequestWrapProcessor());
    }

    /**
     * 建立URL与API BeanName的关系
     */
    private void buildRelationBetweenURLAndBeanName() {
        /*从容器中获取API对象的集合,进行获取*/
        String[] apiBeanNames = applicationContext.getBeanNamesForType(API.class);
        if (apiBeanNames == null) {
            return;
        }
        for (String apiBeanName : apiBeanNames) {
            Class apiClass = applicationContext.getType(apiBeanName);
            if ((apiClass == null) || (!apiClass.isAnnotationPresent(URL.class))) {
                continue;
            }
            String markedUrl = ((URL) apiClass.getAnnotation(URL.class)).url();
            if(StringUtils.isEmpty(markedUrl)){
                continue;
            }
            apiBeanNamesMap.put(markedUrl, apiBeanName);
        }
        /*解决启动毛刺,因为api类的作用于都是prototype,在Spring容器中都是延迟加载,所以需要进行一次预启动。*/
        applicationContext.getBeansOfType(API.class);
    }
}
