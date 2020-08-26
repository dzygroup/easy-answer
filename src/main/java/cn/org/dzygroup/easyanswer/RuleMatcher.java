package cn.org.dzygroup.easyanswer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 戴志勇
 */
public class RuleMatcher {
    private Rule rule;

    private List<RuleAction> ruleActions;


    public RuleMatcher() {
        this.ruleActions = new ArrayList<RuleAction>();
    }

    public RuleMatcher(Rule rule) {
        this(rule, new ArrayList<RuleAction>());
    }

    public RuleMatcher(Rule rule, List<RuleAction> ruleActions) {
        if (rule == null) throw new NullPointerException("rule不能为空");
        if (ruleActions == null) throw new NullPointerException("ruleActions不能为空");
        this.rule = rule;
        this.ruleActions = ruleActions;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public List<RuleAction> getRuleActions() {
        return ruleActions;
    }

    public void setRuleActions(List<RuleAction> ruleActions) {
        if (ruleActions == null) throw new NullPointerException("ruleActions不能为空");
        for (RuleAction ruleAction : ruleActions) {
            if (ruleAction == null) throw new NullPointerException("ruleActions中存在null");
        }
        this.ruleActions = ruleActions;
    }

    public void addRuleAction(RuleAction ruleAction) {
        if (ruleAction == null) throw new RuntimeException("ruleAction不能为空");
        ruleActions.add(ruleAction);
    }
}
