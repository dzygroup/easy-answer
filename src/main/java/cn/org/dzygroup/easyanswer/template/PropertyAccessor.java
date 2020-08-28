package cn.org.dzygroup.easyanswer.template;

import java.util.Map;

/**
 * 属性存取器
 *
 * @author 戴志勇
 */
public interface PropertyAccessor {

    /**
     * 放入属性
     *
     * @param name  属性名
     * @param value 新属性值
     * @return 旧属性值
     */
    Object put(String name, Object value);


    /**
     * 返回属性值
     *
     * @param name 属性名
     * @return 属性值
     */
    Object get(String name);


    /**
     * 删除属性
     *
     * @param name 属性名
     * @return 属性值
     */
    Object remove(String name);


    /**
     * 清空属性值
     */
    void clear();


    /**
     * 获取全部的属性
     *
     * @return 返回全部属性值
     */
    Map<String, Object> getProperties();


    /**
     * 添加一个属性值集合
     *
     * @param properties 属性值集合
     */
    void putAll(Map<String, Object> properties);
}
