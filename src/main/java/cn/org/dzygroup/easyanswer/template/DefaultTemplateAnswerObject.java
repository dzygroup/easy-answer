package cn.org.dzygroup.easyanswer.template;

import java.util.Map;

/**
 * @author 戴志勇
 */
class DefaultTemplateAnswerObject extends AbstractTemplateAnswerObject {

    public DefaultTemplateAnswerObject() {
    }

    public DefaultTemplateAnswerObject(boolean modifiable) {
        super(modifiable);
    }

    public DefaultTemplateAnswerObject(Map<String, Object> templateProperties, boolean modifiable) {
        super(templateProperties, modifiable);
    }

    public DefaultTemplateAnswerObject(Map<String, Object> templateProperties, Map<String, Object> properties, boolean modifiable) {
        super(templateProperties, properties, modifiable);
    }
}
