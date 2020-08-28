package cn.org.dzygroup.easyanswer.template;

import java.util.Map;

/**
 * @author 戴志勇
 */
class DefaultTemplateAnswerObject extends AbstractTemplateAnswerObject {

    public DefaultTemplateAnswerObject() {
    }

    public DefaultTemplateAnswerObject(boolean coverTemplateProp) {
        super(coverTemplateProp);
    }

    public DefaultTemplateAnswerObject(Map<String, Object> templateProperties) {
        super(templateProperties);
    }

    public DefaultTemplateAnswerObject(Map<String, Object> templateProperties, Map<String, Object> customProperties) {
        super(templateProperties, customProperties);
    }

    public DefaultTemplateAnswerObject(Map<String, Object> templateProperties, Map<String, Object> customProperties, boolean coverTemplateProp) {
        super(templateProperties, customProperties, coverTemplateProp);
    }


}
