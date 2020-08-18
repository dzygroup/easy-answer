package org.dzygroup.easyanswer;

/**
 * @author 戴志勇
 */
public interface AnswerHandler<T> {

    T handle(Object result);
}
