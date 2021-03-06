package com.github.dzygroup.easyanswer.template;

import java.util.HashMap;
import java.util.Map;

/**
 * 回答模板
 *
 * @author 戴志勇
 */
public abstract class AbstractTemplateAnswerObject extends AbstractAnswerObject {

    private final Map<String, Object> templateProperties;

    /**
     * 更新模板属性：true: 更新，false: 不更新
     */
    private final boolean modifiable;


    /**
     * 模板回答对象
     */
    public AbstractTemplateAnswerObject() {
        this(false);
    }


    /**
     * 模板回答对象
     *
     * @param modifiable 能否修改模板属性值
     */
    public AbstractTemplateAnswerObject(boolean modifiable) {
        this(new HashMap<String, Object>(), modifiable);
    }

    /**
     * @param templateProperties 模板属性
     * @param modifiable         可否修改模板属性，true可以修改，false不可以修改
     */
    public AbstractTemplateAnswerObject(Map<String, Object> templateProperties, boolean modifiable) {
        this(templateProperties, new HashMap<String, Object>(), modifiable);
    }


    /**
     * @param templateProperties 模板属性键值对
     * @param properties         普通属性键值对，可以使得废模板属性有通用的默认值
     * @param modifiable         可否修改模板属性，true可以修改，false不可以修改
     */
    public AbstractTemplateAnswerObject(Map<String, Object> templateProperties, Map<String, Object> properties, boolean modifiable) {
        super(properties);
        this.templateProperties = templateProperties;
        this.modifiable = modifiable;
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


    /**
     * 删除指定的模板属性，同时会删除回答对象属性
     *
     * @param name 属性名
     * @return 旧的属性值
     */
    public Object removeTemplateProperty(String name) {
        templateProperties.remove(name);
        return super.remove(name);
    }


    /**
     * 清空模板属性，同时会清空回答对象的属性
     */
    public void clearTemplateProperty() {
        for (String name : templateProperties.keySet()) {
            removeTemplateProperty(name);
        }
    }


    /**
     * 是否在模板属性中
     *
     * @param name 水星名
     * @return true存在，false不存在
     */
    protected boolean containsTemplateProperty(String name) {
        return templateProperties.containsKey(name);
    }


    /**
     * 是否可以修改指定属性
     *
     * @param name 属性名
     * @return true可以修改，false不可以修改
     */
    protected boolean canModifyProperty(String name) {
        return modifiable || !containsTemplateProperty(name);
    }
}
