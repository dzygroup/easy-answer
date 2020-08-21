package org.dzygroup.easyanswer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 戴志勇
 */
public class AnswerBuilder {

    private final List<RuleMatcher> ruleMatchers;
    private Computable<?> computable;

    public AnswerBuilder() {
        this.ruleMatchers = new ArrayList<RuleMatcher>();
    }

    public RuleMatcherBuilder matcher() {
        return new RuleMatcherBuilder(this);
    }

    public AnswerBuilder matcher(RuleMatcher ruleMatcher) {
        ruleMatchers.add(ruleMatcher);
        return this;
    }

    /**
     * 计算过程
     *
     * @param computable 计算过程
     * @return 回答构造器
     */
    public AnswerBuilder compute(Computable<?> computable) {
        this.computable = computable;
        return this;
    }

    /**
     * 如果第一个结果返回不为空，则作为新的结果返回。
     *
     * @param <T> 返回响应结果
     * @return 相应结果
     */
    @SuppressWarnings("unchecked")
    public <T> T doAnswer() {
        ComputeResultPair resultPair = compute0();
        Map<String, Object> context = new HashMap<String, Object>();
        for (RuleMatcher ruleMatcher : ruleMatchers) {
            if (ruleMatcher != null && ruleMatcher.getRule().matches(resultPair.getResult(), resultPair.getThrowable())) {
                for (RuleAction ruleAction : ruleMatcher.getRuleActions()) {
                    Object result = ruleAction.action(resultPair.getResult(), resultPair.getThrowable(), context);
                    if (result != null) return (T) result;
                }
            }
        }
        if (resultPair.getThrowable() != null) {
            throw new RuntimeException(resultPair.getThrowable());
        }
        return (T) resultPair.getResult();
    }

    private ComputeResultPair compute0() {
        if (computable == null) {
            throw new RuntimeException("computable不能为空");
        }
        ComputeResultPair pair = new ComputeResultPair();
        try {
            Object result = computable.compute();
            pair.setResult(result);
        } catch (Throwable t) {
            pair.setThrowable(t);
        }
        return pair;
    }
}
