package com.superbaboon.web.framework.core.util;

import java.io.PrintWriter;

/**
 * Created by junjiewu on 16/4/11.
 */
public class PrintWriterUtils {

    public static void print(PrintWriter out, String msg) {
        if (out == null) {
            return;
        }
        out.print(msg);
    }

}
