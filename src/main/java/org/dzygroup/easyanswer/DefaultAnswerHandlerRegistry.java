package org.dzygroup.easyanswer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 戴志勇
 */
public class DefaultAnswerHandlerRegistry implements AnswerHandlerRegistry {

    private final Map<Object, AnswerHandler<?>> handlers;

    public DefaultAnswerHandlerRegistry() {
        this.handlers = new HashMap<Object, AnswerHandler<?>>();
    }

    public void registryAnswerHandler(Object key, AnswerHandler<?> handler) {
        handlers.put(key, handler);
    }

    public AnswerHandler<?> getAnswerHandler(Object key) {
        return handlers.get(key);
    }

    public boolean removeAnswerHandler(Object key) {
        return handlers.remove(key) != null;
    }
}
