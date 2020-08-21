package org.dzygroup.easyanswer;

import java.util.Map;

/**
 * @author 戴志勇
 */
public interface RuleAction {

    /**
     * 当匹配器匹配时执行的动作
     *
     * @param result    匹配器匹配的结果
     * @param throwable 匹配器匹配的异常
     * @return 动作结果
     */
    Object action(Object result, Throwable throwable, Map<String, Object> context);
}
