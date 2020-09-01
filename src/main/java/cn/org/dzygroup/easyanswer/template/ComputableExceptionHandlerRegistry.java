package cn.org.dzygroup.easyanswer.template;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 戴志勇
 */
public abstract class ComputableExceptionHandlerRegistry {

    /**
     * 全局处理器名
     */
    public static final String GLOBAL = "";
    private final Map<String, ComputableExceptionHandler> handlers = new HashMap<String, ComputableExceptionHandler>();

    public ComputableExceptionHandlerRegistry() {
        handlers.put(GLOBAL, new ComputableExceptionHandler() {
            @Override
            public Object handle(AnswerObject answerObject, String name, Throwable t) {
                throw new RuntimeException("属性计算错误", t);
            }
        });
    }

    /**
     * 获取异常处理器
     *
     * @param name 异常处理器名
     * @return 异常处理器
     */
    public ComputableExceptionHandler getExceptionHandler(String name) {
        return handlers.get(name);
    }


    /**
     * 注册异常处理器
     *
     * @param name    异常处理器名
     * @param handler 异常处理器
     * @return 异常处理器
     */
    public ComputableExceptionHandlerRegistry registry(String name, ComputableExceptionHandler handler) {
        handlers.put(name, handler);
        return this;
    }

}
