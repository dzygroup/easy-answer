package cn.org.dzygroup.easyanswer.template;

/**
 * @author 戴志勇
 */
public interface AnswerObjectConverter<T, R> {

    R convert(T t);
}
