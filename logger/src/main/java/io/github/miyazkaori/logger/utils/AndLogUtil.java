package io.github.miyazkaori.logger.utils;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import io.github.miyazkaori.logger.AndLog;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public final class AndLogUtil {

    private static Integer minPriority;

    public static int getMinPriority() {
        if (minPriority == null) {
            minPriority = isDebug() ? Log.VERBOSE : Log.ERROR;
        }
        return minPriority;
    }
    
    public static String getCurrentThreadName() {
    	return Thread.currentThread().getName();
    }

    private static final Set<String> FQCN_IGNORE = new HashSet<>(Arrays.asList(AndLog.class.getName(), AndLogUtil.class.getName()));

    public static String getTag() {
        // if (!isDebug()) return "AndLog"; // Release 模式固定 tag
        StackTraceElement[] stack = new Throwable().getStackTrace();
        for (StackTraceElement element : stack) {
            if (!FQCN_IGNORE.contains(element.getClassName())) {
                String className = element.getClassName();
                return className.substring(className.lastIndexOf(".") + 1);
            }
        }
        return "Unknown";
    }

    private static Application getApplication() {
        try {
            // 反射 android.app.ActivityThread.currentApplication()
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object app = activityThread.getMethod("currentApplication").invoke(null);
            if (app instanceof Application) {
                return (Application) app;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isDebug() {
        Application app = getApplication();
        if (app == null) return false;
        try {
            ApplicationInfo appInfo = app.getApplicationInfo();
            return (appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
