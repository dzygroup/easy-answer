package cn.org.dzygroup.easyanswer.template;

/**
 * @author 戴志勇
 */
public interface ComputablePropertyAccessor extends PropertyAccessor {

    Object put(String name, Computable computable);

    Object put(String name, Computable computable, ExceptionHandler exceptionHandler);
}
