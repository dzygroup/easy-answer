package org.dzygroup.easyanswer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author 戴志勇
 */
public class AnswerAssertTest {

    @Test
    public void assertTrue() {
        DefaultAnswerHandlerRegistry registry = new DefaultAnswerHandlerRegistry();
        registry.registryAnswerHandler(Boolean.TRUE, new AnswerHandler<Boolean>() {
            public Boolean handle(Object result) {
                return Boolean.TRUE;
            }
        });
        DefaultAnswerContext context = new DefaultAnswerContext(registry);
        AnswerAssert answerAssert = new AnswerAssert(context);
        Boolean i = answerAssert.asserts(Boolean.TRUE, new AnswerHandler<Boolean>() {
            public Boolean handle(Object result) {
                if (result == null) return Boolean.FALSE;
                return Boolean.TRUE;
            }
        });
        Assert.assertTrue("", i);
    }
}