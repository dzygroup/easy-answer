package cn.org.dzygroup.easyanswer.template;

import java.util.HashMap;
import java.util.Map;

/**
 * 回答模板
 *
 * @author 戴志勇
 */
public abstract class AbstractTemplateAnswerObject extends AbstractAnswerObject {

    private final Map<String, Object> templateProperties = new HashMap<String, Object>();

    /**
     * 更新模板属性：true: 更新，false: 不更新
     */
    private boolean modifyTemplateProperty;


    /**
     * 模板回答对象
     */
    public AbstractTemplateAnswerObject() {
    }


    /**
     * @param modifyTemplateProperty 存在相同的模板属性是否更新模板属性，false：不更新，true：更新
     */
    public AbstractTemplateAnswerObject(boolean modifyTemplateProperty) {
        this.modifyTemplateProperty = modifyTemplateProperty;
    }


    /**
     * @param templateProperties 模板属性，不能为空
     */
    public AbstractTemplateAnswerObject(Map<String, Object> templateProperties) {
        this(templateProperties, new HashMap<String, Object>());
    }


    /**
     * @param templateProperties 模板属性
     * @param customProperties   自定义属性
     */
    public AbstractTemplateAnswerObject(Map<String, Object> templateProperties, Map<String, Object> customProperties) {
        this(templateProperties, customProperties, false);
    }


    /**
     * @param templateProperties     模板属性
     * @param properties             自定义属性
     * @param modifyTemplateProperty 存在相同的模板属性是否更新模板属性，false：不更新，true：更新
     */
    public AbstractTemplateAnswerObject(Map<String, Object> templateProperties, Map<String, Object> properties, boolean modifyTemplateProperty) {
        if (templateProperties == null) throw new RuntimeException("templateProperties 不能为空");
        if (properties == null) throw new RuntimeException("properties 不能为空");

        this.templateProperties.putAll(templateProperties);
        this.modifyTemplateProperty = modifyTemplateProperty;

        putAll(properties);
    }

    /**
     * 放入模板属性
     *
     * @param name  模板属性名
     * @param value 模板属性值
     * @return 旧的模板属性值
     */
    public Object putTemplateProperty(String name, Object value) {
        templateProperties.put(name, value);
        return super.put(name, value);
    }


    /**
     * 返回原始模板属性值
     *
     * @param name 模板属性名
     * @return 模板属性值
     */
    public Object getTemplateProperty(String name) {
        return templateProperties.get(name);
    }

    public Object removeTemplateProperty(String name) {
        templateProperties.remove(name);
        return super.remove(name);
    }

    public void clearTemplateProperty() {
        for (String name : templateProperties.keySet()) {
            removeTemplateProperty(name);
        }
    }


    @Override
    public Object put(String name, Object value) {
        if (canModifyProperty(name)) {
            return super.put(name, value);
        }
        return null;
    }

    @Override
    public Object remove(String name) {
        if (canModifyProperty(name)) {
            return super.remove(name);
        }
        return null;
    }

    @Override
    public void putAll(Map<String, Object> properties) {
        Map<String, Object> values = new HashMap<String, Object>();
        for (String name : properties.keySet()) {
            if (canModifyProperty(name)) {
                values.put(name, properties.get(name));
            }
        }
        super.putAll(values);
    }

    @Override
    public Object put(String name, Computable computable) {
        if (canModifyProperty(name)) {
            return super.put(name, computable);
        }
        return null;
    }

    @Override
    public Object put(String name, Computable computable, ExceptionHandler exceptionHandler) {
        if (canModifyProperty(name)) {
            return super.put(name, computable, exceptionHandler);
        }
        return null;
    }

    protected boolean containsTemplateProperty(String name) {
        return templateProperties.containsKey(name);
    }

    protected boolean canModifyProperty(String name) {
        return modifyTemplateProperty || !containsTemplateProperty(name);
    }
}
