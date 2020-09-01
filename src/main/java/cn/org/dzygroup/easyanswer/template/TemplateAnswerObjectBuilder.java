package cn.org.dzygroup.easyanswer.template;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板回答对象构建器
 * 将固定的模板属性放入构建器中，可以重复复构建具有相同属性的回答对象。
 *
 * @author 戴志勇
 */
public final class TemplateAnswerObjectBuilder {


    private final ComputableExceptionHandlerRegistry registry;

    /**
     * 模板属性
     */
    private final Map<String, Object> templateProperties;

    /**
     * 属性
     */
    private final Map<String, Object> properties;

    /**
     * 能否修改模板属性，true可以修改，false不可以修改
     */
    private boolean canModifyTemplateProperty;

    /**
     * 标记接口
     */
    private Class<?>[] interfaces;

    private InvocationHandler invocationHandler;


    /**
     * 创建一个模板回答对象
     */
    public TemplateAnswerObjectBuilder() {
        templateProperties = new HashMap<String, Object>();
        properties = new HashMap<String, Object>();
        registry = new DefaultComputableExceptionHandlerRegistry();
    }


    /**
     * 注册全局处理器
     *
     * @param handler 处理器
     * @return {@link TemplateAnswerObjectBuilder}
     */
    public TemplateAnswerObjectBuilder exceptionHandler(ComputableExceptionHandler handler) {
        registry.registry(ComputableExceptionHandlerRegistry.GLOBAL, handler);
        return this;
    }


    /**
     * 添加一个模板属性
     *
     * @param name  属性名
     * @param value 属性值
     * @return 构建器
     */
    public TemplateAnswerObjectBuilder templateProperty(String name, Object value) {
        templateProperties.put(name, value);
        properties.put(name, value);
        return this;
    }


    /**
     * 添加一个模板属性集合
     *
     * @param templateProperties 属性集合
     * @return 构建器
     */
    public TemplateAnswerObjectBuilder templateProperty(Map<String, Object> templateProperties) {
        this.templateProperties.putAll(templateProperties);
        properties.putAll(templateProperties);
        return this;
    }


    /**
     * 添加一个常规属性
     *
     * @param name  属性名
     * @param value 属性值
     * @return 构建器
     */
    public TemplateAnswerObjectBuilder property(String name, Object value) {
        this.properties.put(name, value);
        return this;
    }


    /**
     * 添加一个常规属性集合
     *
     * @param properties 属性集合
     * @return 构建器
     */
    public TemplateAnswerObjectBuilder property(Map<String, Object> properties) {
        this.properties.putAll(properties);
        return this;
    }


    /**
     * 能否修改模板属性
     *
     * @param canModifyTemplateProperty true能够修改，false不能修改
     * @return 构建器
     */
    public TemplateAnswerObjectBuilder canModifyTemplateProperty(boolean canModifyTemplateProperty) {
        this.canModifyTemplateProperty = canModifyTemplateProperty;
        return this;
    }


    /**
     * 构建的{@link AnswerObject}必须实现指定的标记接口
     *
     * @param interfaces 接口
     * @return 构建器
     */
    public TemplateAnswerObjectBuilder mark(Class<?>... interfaces) {
        return mark(interfaces, null);
    }


    /**
     * 标记接口
     *
     * @param interfaces        接口数组
     * @param invocationHandler 接口调用处理器
     * @return {@link TemplateAnswerObjectBuilder}
     */
    public TemplateAnswerObjectBuilder mark(Class<?>[] interfaces, InvocationHandler invocationHandler) {
        if (interfaces == null) throw new NullPointerException("标记接口不能为空");
        for (Class<?> clazz : interfaces) {
            if (!clazz.isInterface()) {
                throw new IllegalArgumentException("标记接口必须是接口");
            }
        }
        this.interfaces = interfaces;
        this.invocationHandler = invocationHandler;
        return this;
    }


    /**
     * 构建一个不实现任何接口的回答对象
     *
     * @return 回答对象
     */
    public AnswerObject build() {
        return build0(new MarkInterfaceInvocationHandler().setBuildList(true), interfaces);
    }


    /**
     * 构建一个实现了{@link Map}接口的回答对象
     *
     * @return {@link AnswerObject}对象
     */
    public AnswerObject buildMapAnswer() {
        return build0(new MarkInterfaceInvocationHandler().setBuildMap(true), Map.class);
    }


    /**
     * 构建一个实现了{@link List}接口的对象
     *
     * @return {@link AnswerObject}对象
     */
    public AnswerObject buildListAnswer() {
        return build0(new MarkInterfaceInvocationHandler().setBuildList(true), List.class);
    }


    private AnswerObject build0(MarkInterfaceInvocationHandler invocationHandler, Class<?>... interfaces) {
        AnswerObject result = new DefaultTemplateAnswerObject(registry, templateProperties, properties, canModifyTemplateProperty);
        if (interfaces != null) {
            Class<?>[] classes = new Class<?>[interfaces.length + 1];
            System.arraycopy(interfaces, 0, classes, 0, interfaces.length);
            classes[interfaces.length] = AnswerObject.class;
            result = (AnswerObject) Proxy.newProxyInstance(getClass().getClassLoader(),
                    classes,
                    invocationHandler.setOtherHandler(this.invocationHandler).setTarget(result));
        }
        return result;
    }

}
