package org.dzygroup.easyanswer;

/**
 * @author 戴志勇
 */
public abstract class AbstractAnswerContext implements AnswerContext {

    private final AnswerHandlerRegistry answerHandlerRegistry;

    public AbstractAnswerContext(AnswerHandlerRegistry answerHandlerRegistry) {
        this.answerHandlerRegistry = answerHandlerRegistry;
    }

    public AnswerHandlerRegistry getAnswerHandlerRegistry() {
        return answerHandlerRegistry;
    }
}
