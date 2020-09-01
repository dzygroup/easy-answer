package com.github.dzygroup.easyanswer.template;

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
    private final PropertyAccessor propertyAccessor;


    /**
     * 创建一个空的属性键值集合
     */
    public AbstractAnswerObject() {
        propertyAccessor = new DefaultPropertyAccessor();
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
        propertyAccessor = new DefaultPropertyAccessor(properties);
    }


    @Override
    public AnswerObject removeAllProperties() {
        propertyAccessor.clear();
        return this;
    }

    @Override
    public AnswerObject set(String name, Object value) {
        propertyAccessor.put(name, value);
        return this;
    }

    @Override
    public AnswerObject set(String name, Computable computable) {
        return set(name, computable, null);
    }

    @Override
    public AnswerObject set(String name, Computable computable, ComputableExceptionHandler computableExceptionHandler) {
        if (computable == null) throw new RuntimeException("computable不能为空");

        Object value;
        try {
            value = computable.compute(get(name));
        } catch (Throwable t) {
            value = handleComputableException(name, computableExceptionHandler, t);
        }
        propertyAccessor.put(name, value);
        return this;
    }

    /**
     * 子类实现异常处理
     *
     * @param name                       设置的属性名
     * @param computableExceptionHandler 异常处理器
     * @param t                          异常
     * @return 属性值
     */
    protected abstract Object handleComputableException(String name, ComputableExceptionHandler computableExceptionHandler, Throwable t);

    @Override
    public AnswerObject set(Map<String, Object> properties) {
        if (properties == null) throw new RuntimeException("属性键值对不能为空");
        propertyAccessor.putAll(properties);
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R> R get(String name) {
        try {
            return (R) propertyAccessor.get(name);
        } catch (ClassCastException e) {
            throw new RuntimeException("类型转换异常", e);
        }
    }

    @Override
    public Map<String, Object> getProperties() {
        return propertyAccessor.getProperties();
    }

    @Override
    public AnswerObject remove(String... names) {
        if (names == null) throw new RuntimeException("参数names不能为空");
        for (String name : names) {
            propertyAccessor.remove(name);
        }
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> R removeAndGet(String name) {
        if (name == null) throw new RuntimeException("参数name不能为空");
        return (R) propertyAccessor.remove(name);
    }

    @Override
    public Map<String, Object> removeAndGet(String... names) {
        if (names == null) throw new RuntimeException("参数names不能为空");
        Map<String, Object> oldValues = new HashMap<String, Object>();
        for (String name : names) {
            oldValues.put(name, propertyAccessor.remove(name));
        }
        return oldValues;
    }

}
