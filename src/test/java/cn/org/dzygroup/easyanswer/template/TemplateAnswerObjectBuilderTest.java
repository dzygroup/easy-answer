package cn.org.dzygroup.easyanswer.template;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author 戴志勇
 */
class TemplateAnswerObjectBuilderTest {

    private TemplateAnswerObjectBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new TemplateAnswerObjectBuilder();
        builder.templateProperty("t1", 1)
                .templateProperty("t2", 2)
                .templateProperty("t3", 3)
                .property("p1", "01")
                .property("p2", "03");
    }

    @Test
    void buildMap() {
        AnswerObject ao = builder.buildMapAnswer();
        JSONObject jo = (JSONObject) JSONObject.toJSON(builder.buildMapAnswer());
        assertEquals(jo.size(), ao.getProperties().size());

        System.out.println(JSONObject.toJSONString(builder.buildMapAnswer()));
    }
}