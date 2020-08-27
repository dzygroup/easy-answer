package cn.org.dzygroup.easyanswer.template;

/**
 * @author 戴志勇
 */
public interface ExceptionHandler {

    Object handle(AnswerObject answerObject, Throwable t);
}
