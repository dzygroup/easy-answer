package cn.org.dzygroup.easyanswer.template;

import com.alibaba.fastjson.JSONArray;
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
        JSONArray aao = (JSONArray) JSONArray.toJSON(builder.buildListAnswer());
        assertEquals(jo.size(), ao.getProperties().size());
        assertEquals(aao.size(), ao.getProperties().values().size());

        System.out.println(JSONObject.toJSONString(builder.buildMapAnswer()));
    }


    @Test
    void remove() {
        AnswerObject ao = builder.buildMapAnswer();
        ao.remove("p1", "p2", "t2");
        JSONObject jo = (JSONObject) JSONObject.toJSON(ao);
        assertEquals(ao.getProperties().size(), jo.size());
    }

    @Test
    void removeTemplateProperty() {
        builder.remove("p1");
        builder.remove("t1");
        AnswerObject ao = builder.buildMapAnswer();
        JSONObject jo = (JSONObject) JSONObject.toJSON(builder.buildMapAnswer());
        JSONArray aao = (JSONArray) JSONArray.toJSON(builder.buildListAnswer());
        assertEquals(jo.size(), ao.getProperties().size());
        assertEquals(aao.size(), ao.getProperties().values().size());
    }
}