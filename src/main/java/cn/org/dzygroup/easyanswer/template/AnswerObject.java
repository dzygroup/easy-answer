package cn.org.dzygroup.easyanswer.template;

import java.util.Map;

/**
 * 回答对象接口
 *
 * @author 戴志勇
 */
public interface AnswerObject {


    AnswerObject set(String name, Object value);

    AnswerObject set(String name, Computable computable);

    AnswerObject set(String name, Computable computable, ComputableExceptionHandler computableExceptionHandler);

    AnswerObject set(Map<String, Object> properties);

    <R> R get(String name);

    Map<String, Object> getProperties();

    AnswerObject remove(String... names);

    <R> R removeAndGet(String name);

    Map<String, Object> removeAndGet(String... names);

    AnswerObject removeAllProperties();
}
