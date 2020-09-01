package cn.org.dzygroup.easyanswer.template;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 戴志勇
 */
public class DefaultPropertyAccessor implements PropertyAccessor {


    /**
     * 属性键值集合
     */
    private final Map<String, Object> properties;

    /**
     * 创建一个空的属性键值集合
     */
    public DefaultPropertyAccessor() {
        properties = new HashMap<String, Object>();
    }

    /**
     * 从{@link Map}对象创建回答对象
     *
     * @param properties 属性键值集合
     */
    public DefaultPropertyAccessor(Map<String, Object> properties) {
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


}
