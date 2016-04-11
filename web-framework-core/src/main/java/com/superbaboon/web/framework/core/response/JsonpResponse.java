package com.superbaboon.web.framework.core.response;

import com.superbaboon.web.framework.core.API;
import com.superbaboon.web.framework.core.Response;
import com.superbaboon.web.framework.core.util.PrintWriterUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by junjiewu on 16/4/11.
 */
public class JsonpResponse implements Response {

    private PrintWriter out;

    private String callback;

    public JsonpResponse(String callback) {
        this.callback = callback;
    }

    public void init(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/x-javascript");
        this.out = httpServletResponse.getWriter();
    }

    public void doSuccess(Object data) {
        String content = JsonFormatter.format(new JsonDataWrapper(API.SUCCESS_ERROR_CODE, data));
        PrintWriterUtils.print(out, JsonpFormatter.format(callback, content));
    }

    public void doError(int code, String msg) {
        String content = JsonFormatter.format(new JsonDataWrapper(code, msg));
        PrintWriterUtils.print(out,JsonpFormatter.format(callback, content));
    }

    public void destroy() {
        IOUtils.closeQuietly(out);
    }

}
