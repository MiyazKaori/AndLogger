package io.github.miyazkaori.logger.impl;
import android.util.Log;
import io.github.miyazkaori.logger.api.AndLogPrinter;
import io.github.miyazkaori.logger.utils.AndLogUtil;
import java.util.Arrays;

public final class ConsoleLogPrinter extends AndLogPrinter {
    @Override
    public String formatMessage(int priority, String currentThreadName, String tag, Throwable throwable, String message, Object... args) {
        try {
            message = String.format(message, args);
        } catch (Exception e) {
            message = message + " " + Arrays.toString(args);
        }
        if(throwable != null) {
        	message = message + "\n" + Log.getStackTraceString(throwable);
        }
        return message;
    }
    
    @Override
    public void println(int priority, String currentThreadName, String tag, String message) {
        Log.println(priority, tag, message);
    }
}