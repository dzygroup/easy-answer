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

    public AnswerBuilder compute(Computable<?> computable) {
        this.computable = computable;
        return this;
    }

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

        throw new RuntimeException("没有返回结果");
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
