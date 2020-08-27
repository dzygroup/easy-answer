package cn.org.dzygroup.easyanswer.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author 戴志勇
 */
class AnswerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void templateAnswer() {
        Answer.templateAnswer().answer();
    }
}