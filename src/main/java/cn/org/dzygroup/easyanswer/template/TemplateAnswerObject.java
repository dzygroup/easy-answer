package cn.org.dzygroup.easyanswer.template;

import java.util.HashMap;
import java.util.Map;

/**
 * 回答模板
 *
 * @author 戴志勇
 */
public abstract class TemplateAnswerObject extends AbstractAnswerObject {

    private final Map<String, Object> templateProperties = new HashMap<String, Object>();

    /**
     * 覆盖模板属性：true: 覆盖，false: 不覆盖
     */
    private boolean coverTemplateProp;


    /**
     * 模板回答对象
     */
    public TemplateAnswerObject() {
    }


    /**
     * @param coverTemplateProp 存在相同的模板属性是否覆盖模板属性，false：不覆盖，true：覆盖
     */
    public TemplateAnswerObject(boolean coverTemplateProp) {
        this.coverTemplateProp = coverTemplateProp;
    }


    /**
     * @param templateProperties 模板属性，不能为空
     */
    public TemplateAnswerObject(Map<String, Object> templateProperties) {
        this(templateProperties, new HashMap<String, Object>());
    }


    /**
     * @param templateProperties 模板属性
     * @param customProperties   自定义属性
     */
    public TemplateAnswerObject(Map<String, Object> templateProperties, Map<String, Object> customProperties) {
        this(templateProperties, customProperties, false);
    }


    /**
     * @param templateProperties 模板属性
     * @param customProperties   自定义属性
     * @param coverTemplateProp  存在相同的模板属性是否覆盖模板属性，false：不覆盖，true：覆盖
     */
    public TemplateAnswerObject(Map<String, Object> templateProperties, Map<String, Object> customProperties, boolean coverTemplateProp) {
        if (templateProperties == null) throw new RuntimeException("templateProperties 不能为空");
        if (customProperties == null) throw new RuntimeException("customProperties 不能为空");

        Map<String, Object> properties;
        if (coverTemplateProp) {
            properties = new HashMap<String, Object>(templateProperties);
            properties.putAll(customProperties);
        } else {
            properties = new HashMap<String, Object>(customProperties);
            properties.putAll(templateProperties);
        }

        super.putAll(properties);

        this.coverTemplateProp = coverTemplateProp;
    }

    /**
     * 放入模板属性
     *
     * @param name  模板属性名
     * @param value 模板属性值
     * @return 旧的模板属性值
     */
    public Object putTemplateProperty(String name, Object value) {
        return templateProperties.put(name, value);
    }


    /**
     * 返回模板属性值
     *
     * @param name 模板属性名
     * @return 模板属性值
     */
    public Object getTemplateProperty(String name) {
        return templateProperties.get(name);
    }


    @Override
    public Object put(String name, Object value) {
        // 存在相同的模板属性值时覆盖
        if (coverTemplateProp && templateProperties.containsKey(name)) {
            putTemplateProperty(name, value);
        }
        return super.put(name, value);
    }

    @Override
    public Object get(String name) {
        // 不覆盖
        if (!coverTemplateProp && templateProperties.containsKey(name)) {
            return templateProperties.get(name);
        }
        return super.get(name);
    }

    @Override
    public Object remove(String name) {
        // 不覆盖
        if (!coverTemplateProp && templateProperties.containsKey(name)) {
            return templateProperties.get(name);
        }
        return super.remove(name);
    }

    public Object removeTemplateProperty(String name) {
        return templateProperties.remove(name);
    }


    public void clearTemplateProperty() {
        templateProperties.clear();
    }
}
