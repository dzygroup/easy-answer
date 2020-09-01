package cn.org.dzygroup.easyanswer.template;

/**
 * 可计算的属性访问器
 *
 * @author 戴志勇
 */
public interface ComputablePropertyAccessor extends PropertyAccessor {

    /**
     * {@link Computable}计算的结果作为属性值
     *
     * @param name       属性名
     * @param computable 可计算的接口
     * @return 旧的属性值
     */
    Object put(String name, Computable computable);


    /**
     * {@link Computable}计算的结果作为属性值，计算发生异常时回调用指定的异常处理器{@link ComputableExceptionHandler}
     *
     * @param name                       属性名
     * @param computable                 可计算接口
     * @param computableExceptionHandler 异常处理器
     * @return 旧的属性值
     */
    Object put(String name, Computable computable, ComputableExceptionHandler computableExceptionHandler);
}
