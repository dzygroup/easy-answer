package cn.org.dzygroup.easyanswer.template;

import java.util.Map;

/**
 * @author 戴志勇
 */
public class MapConverter implements AnswerObjectConverter<AnswerObject, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(AnswerObject answerObject) {
        return answerObject.getProperties();
    }
}
