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
public class JsonResponse implements Response {

    private PrintWriter out;

    public void init(HttpServletResponse httpServletResponse) throws IOException {
        this.out = httpServletResponse.getWriter();
    }

    public void doSuccess(Object data) {
        PrintWriterUtils.print(out, JsonFormatter.format(new JsonDataWrapper(API.SUCCESS_ERROR_CODE, data)));
    }

    public void doError(int code, String msg) {
        PrintWriterUtils.print(out, JsonFormatter.format(new JsonDataWrapper(code, msg)));
    }

    public void destroy() {
        IOUtils.closeQuietly(out);
    }

}
