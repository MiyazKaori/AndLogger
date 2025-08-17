package io.github.miyazkaori.logger;

import android.util.Log;
import io.github.miyazkaori.logger.api.AndLogPrinter;
import io.github.miyazkaori.logger.utils.AndLogUtil;
import java.util.List;
import io.github.miyazkaori.logger.api.AndLogPrinter;
import io.github.miyazkaori.logger.impl.ConsoleLogPrinter;
import java.util.concurrent.CopyOnWriteArrayList;

public final class AndLog {

    public static final List<AndLogPrinter> printerList = new CopyOnWriteArrayList<>();

    private static void addPrinter(AndLogPrinter printer) {
    	printerList.add(printer);
    }

    static {
        addPrinter(new ConsoleLogPrinter());
    }
    
    public static void v(String message, Object... args) {
        log(Log.VERBOSE, message, args);
    }
    
    public static void d(String message, Object... args) {
        log(Log.DEBUG, message, args);
    }
    
    public static void i(String message, Object... args) {
        log(Log.INFO, message, args);
    }

    public static void w(String message, Object... args) {
        log(Log.WARN, message, args);
    }
    
    public static void w(Throwable throwable, String message, Object... args) {
        log(Log.WARN, throwable, message, args);
    }

    public static void e(String message, Object... args) {
        log(Log.ERROR, message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        log(Log.ERROR, throwable, message, args);
    }
    
    private static void log(int priority, String message, Object... args) {
        log(priority, null, message, args);
    }

    private static void log(int priority, Throwable throwable, String message, Object... args) {
        for(AndLogPrinter printer: printerList) {
            printer.printIfAllowed(priority, AndLogUtil.getCurrentThreadName(), AndLogUtil.getTag(), throwable, message, args);
        }
    }
}