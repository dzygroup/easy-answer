package cn.org.dzygroup.easyanswer.template;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟回答对象
 *
 * @author 戴志勇
 */
public abstract class AbstractAnswerObject implements AnswerObject {

    /**
     * 属性键值集合
     */
    private final Map<String, Object> properties;

    /**
     * 创建一个空的属性键值集合
     */
    public AbstractAnswerObject() {
        properties = new HashMap<String, Object>();
    }

    /**
     * 从{@link Map}对象创建回答对象
     *
     * @param properties 属性键值集合
     */
    public AbstractAnswerObject(Map<String, Object> properties) {
        if (properties == null) {
            throw new RuntimeException("properties 不能为空");
        }
        this.properties = new HashMap<String, Object>(properties);
    }


    /**
     * 放入一个新的属性值
     *
     * @param name  属性名
     * @param value 新属性值
     * @return 旧的属性名
     */
    @Override
    public Object put(String name, Object value) {
        return properties.put(name, value);
    }


    /**
     * 获取指定属性值
     *
     * @param name 属性名
     * @return 属性值
     */
    @Override
    public Object get(String name) {
        return properties.get(name);
    }


    /**
     * 删除指定属性
     *
     * @param name 属性名
     * @return 旧的属性值
     */
    @Override
    public Object remove(String name) {
        return properties.remove(name);
    }


    /**
     * 清空全部属性
     */
    @Override
    public void clear() {
        properties.clear();
    }


    /**
     * 获取回答对象的全部属性
     *
     * @return 属性键值集合
     */
    @Override
    public Map<String, Object> getProperties() {
        return new HashMap<String, Object>(properties);
    }


    /**
     * 放入一个属性值集合
     * <p>
     * 如果一个回答对象的模板属性不可以修改，则不会更新。
     *
     * @param properties 属性值集合
     */
    @Override
    public void putAll(Map<String, Object> properties) {
        if (properties == null) throw new RuntimeException("properties 不能为空");
        this.properties.putAll(properties);
    }


    /**
     * 放入一个属性值
     * <p>
     * 将{@link Computable}执行结果作为新的属性值
     *
     * @param name       属性名
     * @param computable 可计算对象
     * @return 旧的值
     */
    @Override
    public Object put(String name, Computable computable) {
        if (computable == null) throw new RuntimeException("computable不能为空");
        return properties.put(name, computable.compute());
    }


    /**
     * 放入一个属性值
     * <p>
     * 将{@link Computable}执行结果作为新的属性值，如果计算过程发生异常，将会触发异常处理器，异常处理器的结果作为新的值，如果异常处理器返回
     * {@link SkipNext}对象则不会设置新值。
     *
     * @param name             属性名
     * @param computable       可计算对象
     * @param exceptionHandler 异常处理器
     * @return 旧的值
     */
    @Override
    public Object put(String name, Computable computable, ExceptionHandler exceptionHandler) {
        if (computable == null) throw new RuntimeException("computable不能为空");

        Object value;
        try {
            value = computable.compute();
        } catch (Throwable t) {
            if (exceptionHandler != null) {
                value = exceptionHandler.handle(this, t);
            } else {
                throw new RuntimeException(t);
            }
        }

        if (value instanceof SkipNext) {
            return properties.get(name);
        }

        properties.put(name, value);
        return value;
    }
}
