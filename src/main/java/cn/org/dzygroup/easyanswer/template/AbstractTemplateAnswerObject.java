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

        super.putAll(templateProperties);
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
     * 放入一个回答对象属性
     * 如果回答对象属性是模板属性，则会判断是否可以更改模板属性，如果不可以修改，则会放入失败，返回null，否则放入成功，返回旧的属性
     *
     * @param name  属性名
     * @param value 属性值
     * @return 旧的属性值
     */
    @Override
    public Object put(String name, Object value) {
        if (canModifyProperty(name)) {
            return super.put(name, value);
        }
        return null;
    }


    /**
     * 删除一个属性。
     * <p>
     * 如果待删除属性是模板属性且模板属性不可以修改，那么删除失败，返回null，否则删除成功，返回旧的属性值。
     *
     * @param name 属性名
     * @return 旧的属性值
     */
    @Override
    public Object remove(String name) {
        if (canModifyProperty(name)) {
            return super.remove(name);
        }
        return null;
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
        Map<String, Object> values = new HashMap<String, Object>();
        for (String name : properties.keySet()) {
            if (canModifyProperty(name)) {
                values.put(name, properties.get(name));
            }
        }
        super.putAll(values);
    }


    /**
     * 放入一个属性值
     * <p>
     * 将{@link Computable}执行结果作为新的属性值
     *
     * @param name       属性名
     * @param computable 可计算对象
     * @return 旧的值
     */
    @Override
    public Object put(String name, Computable computable) {
        if (canModifyProperty(name)) {
            return super.put(name, computable);
        }
        return null;
    }


    /**
     * 放入一个属性值
     * <p>
     * 将{@link Computable}执行结果作为新的属性值，如果计算过程发生异常，将会触发异常处理器，异常处理器的结果作为新的值，如果异常处理器返回
     * {@link SkipNext}对象则不会设置新值。
     *
     * @param name       属性名
     * @param computable 可计算对象
     * @return 旧的值
     */
    @Override
    public Object put(String name, Computable computable, ExceptionHandler exceptionHandler) {
        if (canModifyProperty(name)) {
            return super.put(name, computable, exceptionHandler);
        }
        return null;
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
        return modifyTemplateProperty || !containsTemplateProperty(name);
    }
}
