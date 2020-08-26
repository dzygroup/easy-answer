package cn.org.dzygroup.easyanswer;

/**
 * @author 戴志勇
 */
public interface Converter<T, R> {

    R convert(T t);
}
