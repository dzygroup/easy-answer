package org.dzygroup.easyanswer.template;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 戴志勇
 */
public class AnswerTemplate {

    private final Map<String, Object> FIXED_PROPERTIES = new HashMap<String, Object>();

    public AnswerTemplate() {
    }


    public Object putFixProperty(String name, Object value) {
        return FIXED_PROPERTIES.put(name, value);
    }

    public Object answerContent(String name, Object value) {
        return new AnswerContent(new HashMap<String, Object>(FIXED_PROPERTIES));
    }

    public static class AnswerContent {
        private Map<String, Object> properties = new HashMap<String, Object>();

        public AnswerContent() {
        }

        public AnswerContent(Map<String, Object> properties) {
            this.properties = properties;
        }

        public AnswerContent putProperties(Map<String, Object> properties) {
            this.properties.putAll(properties);
            return this;
        }

        public AnswerContent putProperty(String name, Object value) {
            properties.put(name, value);
            return this;
        }

        public Map<String, Object> toMap() {
            return new HashMap<String, Object>(properties);
        }
    }
}
