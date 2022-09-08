package vjvm.util;

import lombok.var;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static StringBuilder getHeader(String level) {
        var caller = Thread.currentThread().getStackTrace()[3];
        String fileName = caller.getFileName();
        String methodName = caller.getMethodName();
        int lineNumber = caller.getLineNumber();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");
        Date date = new Date(System.currentTimeMillis());

        StringBuilder builder = new StringBuilder();
        builder.append(formatter.format(date))
            .append(" [")
            .append(level)
            .append(" (")
            .append(fileName)
            .append(":")
            .append(lineNumber)
            .append(") ")
            .append(methodName)
            .append("] - ");
        return builder;
    }

    public static void trace(String s) {
        System.err.println(getHeader("TRACE").append(s));
    }

    public static void debug(String s) {
        System.err.println(getHeader("DEBUG").append(s));
    }

    public static void info(String s) {
        System.err.println(getHeader("INFO").append(s));
    }

    public static void print(Object s) {
        System.out.print(s);
    }

    public static void println(Object s) {
        System.out.println(s);
    }
}
