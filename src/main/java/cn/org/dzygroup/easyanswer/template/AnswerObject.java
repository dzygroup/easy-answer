package cn.org.dzygroup.easyanswer.template;

import java.util.Map;

/**
 * @author 戴志勇
 */
public interface AnswerObject extends PropertyAccessor {

    Map<String, Object> toMap();


}
