package org.dzygroup.easyanswer;

/**
 * 应答上下文
 *
 * @author 戴志勇
 */
public interface AnswerContext {

    /**
     * 获得{@link AnswerHandlerRegistry}
     *
     * @return {@link AnswerHandlerRegistry}对象
     * @see AnswerHandlerRegistry
     */
    AnswerHandlerRegistry getAnswerHandlerRegistry();
}
