package org.dzygroup.easyanswer.template;

import org.junit.Test;

/**
 * @author 戴志勇
 */
public class AnswerTemplateTest {

    @Test
    public void putFixProperty() {
        AnswerTemplate template = new AnswerTemplate();
        template.putFixProperty("code", "00001");
        template.putFixProperty("message", "message");
        template.answerContent();
    }

    @Test
    public void answerContent() {
    }
}