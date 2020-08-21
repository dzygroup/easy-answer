package org.dzygroup.easyanswer;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author 戴志勇
 */
public class EasyAnswerTest {

    @Test
    public void commonAnswer() {
        ResultObject r1 = EasyAnswer.commonAnswer(new Computable<Object>() {
            @Override
            public Object compute() {
                return null;
            }
        }).onException(new ExceptionCallback() {
            @Override
            public Object execute(Throwable t) {
                return new ResultObject("r1");
            }
        }).onFinish(new FinishCallback() {
            @Override
            public Object execute(Object result) {
                return new ResultObject("r1");
            }
        }).doAnswer();

        assertEquals("r1", r1.name);

    }

    @Test
    public void answer() {

        ResultObject r2 = EasyAnswer.answer().compute(new Computable<Object>() {
            @Override
            public Object compute() {
                return new ResultObject("r2");
            }
        }).matcher().rule(new Rule() {
            @Override
            public boolean matches(Object result, Throwable throwable) {
                return result != null && throwable == null;
            }
        }).ruleAction(new RuleAction() {
            @Override
            public Object action(Object result, Throwable throwable, Map<String, Object> context) {
                return new ResultObject("r2");
            }
        }).end().doAnswer();

        assertEquals(r2.name, "r2");
    }

    static class ResultObject {
        public String name;

        public ResultObject(String name) {
            this.name = name;
        }
    }
}