package com.github.dzygroup.easyanswer.template;

import java.util.Map;

/**
 * 回答对象接口
 *
 * @author 戴志勇
 */
public interface AnswerObject {

    /**
     * 设置属性值
     *
     * @param name  属性名
     * @param value 属性值
     * @return {@link AnswerObject}
     */
    AnswerObject set(String name, Object value);


    /**
     * 设置属性
     *
     * @param name       属性名
     * @param computable 可计算属性值
     * @return {@link AnswerObject}
     */
    AnswerObject set(String name, Computable computable);


    /**
     * 设置属性值。
     * 通过{@link Computable}计算属性值，并将计算结果作为最终的属性值，如果该计算过程发生异常，那么会将异常传递给
     * {@code computableExceptionHandler}对象，执行后续的处理。
     *
     * @param name                       属性名
     * @param computable                 可计算属性值
     * @param computableExceptionHandler 可计算属性值异常处理器
     * @return {@link AnswerObject}
     */
    AnswerObject set(String name, Computable computable, ComputableExceptionHandler computableExceptionHandler);


    /**
     * 设置属性值
     *
     * @param properties 属性键值对
     * @return {@link AnswerObject}
     */
    AnswerObject set(Map<String, Object> properties);


    /**
     * 获取属性值
     *
     * @param name 属性名
     * @param <R>  返回结果类型
     * @return 返回结果
     */
    <R> R get(String name);


    /**
     * 获取全部的属性键值对
     *
     * @return map集合，键为属性名，值为属性值
     */
    Map<String, Object> getProperties();


    /**
     * 删除属性
     *
     * @param names 属性名数组
     * @return {@link AnswerObject}
     */
    AnswerObject remove(String... names);


    /**
     * 删除并返回删除的值
     *
     * @param name 属性名
     * @param <R>  属性值类型
     * @return 被删除属性的值
     */
    <R> R removeAndGet(String name);


    /**
     * 删除并返回删除的属性的值
     *
     * @param names 属性值
     * @return 属性值类型
     */
    Map<String, Object> removeAndGet(String... names);


    /**
     * 移除所有属性
     *
     * @return {@link AnswerObject}
     */
    AnswerObject removeAllProperties();
}
