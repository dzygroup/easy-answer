package cn.org.dzygroup.easyanswer.template;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 戴志勇
 */
public final class TemplateAnswerObjectBuilder {

    private final Map<String, Object> templateProperties;
    private final Map<String, Object> properties;
    private boolean canModifyTemplateProperty;

    public TemplateAnswerObjectBuilder() {
        templateProperties = new HashMap<String, Object>();
        properties = new HashMap<String, Object>();
    }

    public TemplateAnswerObjectBuilder templateProperty(String name, Object value) {
        templateProperties.put(name, value);
        return this;
    }

    public TemplateAnswerObjectBuilder templateProperty(Map<String, Object> templateProperties) {
        this.templateProperties.putAll(templateProperties);
        return this;
    }

    public TemplateAnswerObjectBuilder property(String name, Object value) {
        this.properties.put(name, value);
        return this;
    }

    public TemplateAnswerObjectBuilder property(Map<String, Object> properties) {
        this.properties.putAll(properties);
        return this;
    }

    public TemplateAnswerObjectBuilder canModifyTemplateProperty(boolean canModifyTemplateProperty) {
        this.canModifyTemplateProperty = canModifyTemplateProperty;
        return this;
    }

    public AnswerObject build() {
        return new DefaultTemplateAnswerObject(templateProperties, properties, canModifyTemplateProperty);
    }

    public AnswerObject buildMapAnswer() {
        return (AnswerObject) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class<?>[]{Map.class, AnswerObject.class},
                new MapInvocationHandler(build()));
    }

    public AnswerObject buildListAnswer() {
        return (AnswerObject) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class<?>[]{List.class, AnswerObject.class},
                new ListInvocationHandler(build()));
    }


}
