package com.example.config;


/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2018/6/28.
 */
public class LogUtil {

    private static final ThreadLocal<LogResult> LOG_MODEL = new ThreadLocal<>();

    public static LogResult getLogResult() {
        return LOG_MODEL.get();
    }

    public static void setLogResult(LogResult logResult) {
        LOG_MODEL.set(logResult);
    }

    public static void removeLogResult() {
        LOG_MODEL.remove();
    }

    public static void setLogResultAttr(String key, String val) {
        LOG_MODEL.get().setMoreInfo(key, val);
    }

}
