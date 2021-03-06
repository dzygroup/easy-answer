package com.github.dzygroup.easyanswer.template;

/**
 * @author 戴志勇
 */
public interface ComputableExceptionHandler {

    /**
     * 异常处理器
     *
     * @param answerObject {@link AnswerObject}对象
     * @param name         属性名
     * @param t            发生的异常
     * @return 结果
     */
    Object handle(AnswerObject answerObject, String name, Throwable t);
}
