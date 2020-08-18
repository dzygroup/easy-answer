package org.dzygroup.easyanswer;

/**
 * @author 戴志勇
 */
public class AnswerAssert {
    private final AnswerContext answerContext;

    public AnswerAssert(AnswerContext answerContext) {
        this.answerContext = answerContext;
    }

    @SuppressWarnings("unchecked")
    public <T> T asserts(Object key, Object result) {
        AnswerHandler<?> handler = answerContext.getAnswerHandlerRegistry().getAnswerHandler(key);
        return (T) (handler == null ? result : handler.handle(result));
    }

}
