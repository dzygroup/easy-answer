package cn.org.dzygroup.easyanswer.template;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 戴志勇
 */
class DefaultTemplateAnswerObject extends AbstractTemplateAnswerObject {

    private ComputableExceptionHandlerRegistry registry;

    public DefaultTemplateAnswerObject() {
    }

    public DefaultTemplateAnswerObject(ComputableExceptionHandlerRegistry registry) {
        this(registry, new HashMap<String, Object>(), new HashMap<String, Object>(), false);
    }

    public DefaultTemplateAnswerObject(ComputableExceptionHandlerRegistry registry, boolean modifiable) {
        this(registry, new HashMap<String, Object>(), new HashMap<String, Object>(), modifiable);
    }

    public DefaultTemplateAnswerObject(ComputableExceptionHandlerRegistry registry, Map<String, Object> templateProperties, boolean modifiable) {
        this(registry, templateProperties, new HashMap<String, Object>(), modifiable);
    }

    public DefaultTemplateAnswerObject(ComputableExceptionHandlerRegistry registry, Map<String, Object> templateProperties, Map<String, Object> properties, boolean modifiable) {
        super(templateProperties, properties, modifiable);
        this.registry = registry;
    }


    @Override
    protected Object handleComputableException(String name, ComputableExceptionHandler computableExceptionHandler, Throwable t) {
        Object value;
        if (computableExceptionHandler != null) {
            value = computableExceptionHandler.handle(this, name, t);
        } else {
            ComputableExceptionHandler exceptionHandler = registry.getExceptionHandler(ComputableExceptionHandlerRegistry.GLOBAL);
            if (exceptionHandler == null) {
                throw new RuntimeException("没有设置全局处理器或者局部处理器", t);
            } else {
                value = exceptionHandler.handle(this, name, t);
            }
        }
        return value;
    }
}
