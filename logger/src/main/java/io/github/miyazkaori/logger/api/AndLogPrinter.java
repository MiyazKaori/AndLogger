package io.github.miyazkaori.logger.api;
import android.util.Log;
import io.github.miyazkaori.logger.utils.AndLogUtil;

public abstract class AndLogPrinter {

    private int minPriority;
    
    public AndLogPrinter() {
        this(AndLogUtil.getMinPriority());
    }
    
    public AndLogPrinter(int minPriority) {
        if (minPriority < Log.VERBOSE) {
            minPriority = Log.VERBOSE;
        }
        this.minPriority = minPriority;
    }
    
    public void printIfAllowed(int priority, String currentThreadName, String tag, Throwable throwable, String message, Object... args) {
        if (priority >= this.minPriority) {
            println(priority, currentThreadName, tag, formatMessage(priority, currentThreadName, tag, throwable, message, args));
        }
    }
    
    public abstract String formatMessage(int priority, String currentThreadName, String tag, Throwable throwable, String message, Object... args);

    public abstract void println(int priority, String currentThreadName, String tag, String message);
}
