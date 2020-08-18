package org.dzygroup.easyanswer;

/**
 * 注册应答处理器{@link AnswerHandler}
 *
 * @author 戴志勇
 */
public interface AnswerHandlerRegistry {

    /**
     * 注册应答处理器
     *
     * @param key     应答处理器的名字
     * @param handler 应答处理器{@link AnswerHandler}
     * @see AnswerHandler
     */
    void registryAnswerHandler(Object key, AnswerHandler<?> handler);

    /**
     * 从注册器中获得{@link AnswerHandler}
     *
     * @param key 键名
     * @return {@link AnswerHandler}对象或{@code null}
     * @see AnswerHandler
     */
    AnswerHandler<?> getAnswerHandler(Object key);

    /**
     * 从注册器中移除{@link AnswerHandler}
     *
     * @param key 键名
     * @return true移除，false未移除
     * @see AnswerHandler
     */
    boolean removeAnswerHandler(Object key);
}
