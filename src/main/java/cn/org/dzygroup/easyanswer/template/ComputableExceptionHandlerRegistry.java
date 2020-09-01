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

    public ComputableExceptionHandler getExceptionHandler(String name) {
        return handlers.get(name);
    }

    public ComputableExceptionHandlerRegistry registry(String name, ComputableExceptionHandler handler) {
        handlers.put(name, handler);
        return this;
    }

}
