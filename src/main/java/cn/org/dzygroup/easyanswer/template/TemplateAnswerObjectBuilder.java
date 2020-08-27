package cn.org.dzygroup.easyanswer.template;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 戴志勇
 */
public class TemplateAnswerObjectBuilder {

    private final Map<String, Object> templateProperties;
    private final Map<String, Object> properties;
    private boolean canUpdateTemplateProperty;

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

    public TemplateAnswerObjectBuilder property(String name, String value) {
        this.properties.put(name, value);
        return this;
    }

    public TemplateAnswerObjectBuilder property(Map<String, Object> properties) {
        this.properties.putAll(properties);
        return this;
    }

    public TemplateAnswerObjectBuilder canUpdateTemplateProperty(boolean canUpdateTemplateProperty) {
        this.canUpdateTemplateProperty = canUpdateTemplateProperty;
        return this;
    }

    public AnswerObject answer() {
        return new DefaultAnswerObject(templateProperties, properties, canUpdateTemplateProperty);
    }
}
