package org.dzygroup.easyanswer;

import java.util.Collection;

/**
 * @author 戴志勇
 */
public class RuleMatcherBuilder {

    private final RuleMatcher ruleMatcher;

    private final AnswerBuilder answerBuilder;

    /**
     * 创建一个规则匹配器的构建器
     *
     * @param answerBuilder 一个回答构建器
     */
    public RuleMatcherBuilder(AnswerBuilder answerBuilder) {
        ruleMatcher = new RuleMatcher();
        this.answerBuilder = answerBuilder;
    }

    /**
     * 设置匹配规则。
     *
     * <p>
     * {@code rule}不能为空
     * </p>
     *
     * @param rule 匹配规则{@link Rule}
     * @return 规则匹配器构建器
     * @see Rule
     */
    public RuleMatcherBuilder rule(Rule rule) {
        if (rule == null) {
            throw new NullPointerException("rule不能为空");
        }
        ruleMatcher.setRule(rule);
        return this;
    }

    /**
     * 规则匹配时的规则动作
     *
     * @param ruleAction 规则动作
     * @return 返回构建器
     */
    public RuleMatcherBuilder ruleAction(RuleAction ruleAction) {
        ruleMatcher.addRuleAction(ruleAction);
        return this;
    }

    /**
     * 规则匹配时的规则动作。
     *
     * @param ruleActions 规则动作集合
     * @return 构建器
     */
    public RuleMatcherBuilder ruleActions(Collection<RuleAction> ruleActions) {
        if (ruleActions == null) return this;
        for (RuleAction action : ruleActions) {
            if (action != null) ruleMatcher.addRuleAction(action);
        }
        return this;
    }

    /**
     * 下一个规则匹配器
     *
     * @return 下一个规则匹配器
     */
    public RuleMatcherBuilder next() {
        answerBuilder.matcher(ruleMatcher);
        return new RuleMatcherBuilder(answerBuilder);
    }

    public AnswerBuilder end() {
        answerBuilder.matcher(ruleMatcher);
        return answerBuilder;
    }
}
