package org.dzygroup.easyanswer;


import org.junit.Test;

/**
 * @author 戴志勇
 */

public class DefaultAnswerContextTest {

    @Test
    public void test() {
        DefaultAnswerHandlerRegistry registry = new DefaultAnswerHandlerRegistry();
        registry.registryAnswerHandler(Boolean.TRUE, new AnswerHandler<Boolean>() {
            @Override
            public Boolean handle(Object result) {
                return Boolean.TRUE;
            }
        });
        DefaultAnswerContext context = new DefaultAnswerContext(registry);
    }


}