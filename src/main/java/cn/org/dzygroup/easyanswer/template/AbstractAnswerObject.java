package cn.org.dzygroup.easyanswer.template;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟回答对象
 *
 * @author 戴志勇
 */
public abstract class AbstractAnswerObject implements AnswerObject {

    private final Map<String, Object> properties;

    public AbstractAnswerObject() {
        properties = new HashMap<String, Object>();
    }

    public AbstractAnswerObject(Map<String, Object> properties) {
        if (properties == null) {
            throw new RuntimeException("properties 不能为空");
        }
        this.properties = new HashMap<String, Object>(properties);
    }

    @Override
    public Object put(String name, Object value) {
        return properties.put(name, value);
    }

    @Override
    public Object get(String name) {
        return properties.get(name);
    }

    @Override
    public Object remove(String name) {
        return properties.remove(name);
    }

    @Override
    public void clear() {
        properties.clear();
    }

    @Override
    public Map<String, Object> getProperties() {
        return new HashMap<String, Object>(properties);
    }

    @Override
    public void putAll(Map<String, Object> properties) {
        if (properties == null) throw new RuntimeException("properties 不能为空");
        this.properties.putAll(properties);
    }
}
