package cn.org.dzygroup.easyanswer.template;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 戴志勇
 */
public class AnswerObjectBuilder {

    private final Map<String, Object> templateProperties;
    private final Map<String, Object> properties;
    private boolean canModifyTemplateProperty;

    public AnswerObjectBuilder() {
        templateProperties = new HashMap<String, Object>();
        properties = new HashMap<String, Object>();
    }

    public AnswerObjectBuilder templateProperty(String name, Object value) {
        templateProperties.put(name, value);
        return this;
    }

    public AnswerObjectBuilder templateProperty(Map<String, Object> templateProperties) {
        this.templateProperties.putAll(templateProperties);
        return this;
    }

    public AnswerObjectBuilder property(String name, Object value) {
        this.properties.put(name, value);
        return this;
    }

    public AnswerObjectBuilder property(Map<String, Object> properties) {
        this.properties.putAll(properties);
        return this;
    }

    public AnswerObjectBuilder canModifyTemplateProperty(boolean canModifyTemplateProperty) {
        this.canModifyTemplateProperty = canModifyTemplateProperty;
        return this;
    }

    public AnswerObject build() {
        return new DefaultAnswerObject(templateProperties, properties, canModifyTemplateProperty);
    }
}
