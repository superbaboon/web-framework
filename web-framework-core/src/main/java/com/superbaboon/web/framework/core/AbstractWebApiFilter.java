package com.superbaboon.web.framework.core;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * abstract the lifecycle of request process
 *
 * Created by junjiewu on 16/4/5.
 */
public abstract class AbstractWebApiFilter implements Filter {

    private List<ApiExecutePreProcessor> apiExecutePreProcessorList;

    private List<ApiExecutePostProcessor> apiExecutePostProcessorList;

    private List<URLFilter> urlFilterList;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // if request should be blocked
        boolean isThisUrlShouldBeIntercepted = checkUrlShouldBeIntecepted(httpServletRequest);
        if (isThisUrlShouldBeIntercepted) {
            chain.doFilter(request, response);
            return;
        }

        // find api to execute business logic
        API api = findMatchedAPI(httpServletRequest);
        if (api == null) {
            chain.doFilter(request, response);
            return;
        }

        Response res = findMatchedResponse(httpServletRequest);
        try {
            res.init(httpServletResponse);

            processBeforeApiExecute(api, httpServletRequest, httpServletResponse);

            Object data = api.execute();

            processAfterApiExecute(api, httpServletRequest, httpServletResponse);

            res.doSuccess(data);
        } catch (MessageAwareException e) {
            ErrorCode errorCode = e.getErrorCode();
            res.doError(errorCode.getErrorCode(), errorCode.getErrorMessage());
        } catch (Throwable t) {
            res.doError(API.SERVER_ERROR_CODE, "服务器开小差了~");
        } finally {
            res.destroy();
        }
    }

    private void processAfterApiExecute(API api, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        for (ApiExecutePostProcessor apiExecutePostProcessor : apiExecutePostProcessorList) {
            apiExecutePostProcessor.postProcess(api, httpServletRequest, httpServletResponse);
        }
    }

    private void processBeforeApiExecute(API api, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        for (ApiExecutePreProcessor apiExecutePreProcessor : apiExecutePreProcessorList) {
            apiExecutePreProcessor.preProcess(api, httpServletRequest, httpServletResponse);
        }
    }

    private boolean checkUrlShouldBeIntecepted(HttpServletRequest httpServletRequest) {
        for (URLFilter urlFilter : urlFilterList) {
            if (urlFilter.needFilter(httpServletRequest)) {
                return true;
            }
        }
        return false;
    }

    protected abstract Response findMatchedResponse(HttpServletRequest httpServletRequest);

    protected abstract API findMatchedAPI(HttpServletRequest httpServletRequest);

    public void init(FilterConfig filterConfig) throws ServletException {
        this.apiExecutePreProcessorList = new ArrayList<ApiExecutePreProcessor>();
        this.apiExecutePostProcessorList = new ArrayList<ApiExecutePostProcessor>();
        this.urlFilterList = new ArrayList<URLFilter>();

        doInit(filterConfig);
    }

    protected abstract void doInit(FilterConfig filterConfig);

    protected void addApiExecutePreProcessor(ApiExecutePreProcessor apiExecutePreProcessor) {
        this.apiExecutePreProcessorList.add(apiExecutePreProcessor);
    }

    protected void addApiExecutePostProcessor(ApiExecutePostProcessor apiExecutePostProcessor) {
        this.apiExecutePostProcessorList.add(apiExecutePostProcessor);
    }

    protected void addUrlFilter(URLFilter urlFilter) {
        this.urlFilterList.add(urlFilter);
    }

    public void destroy() {
    }
}
