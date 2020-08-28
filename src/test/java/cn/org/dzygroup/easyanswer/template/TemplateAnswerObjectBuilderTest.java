package cn.org.dzygroup.easyanswer.template;

import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author 戴志勇
 */
class TemplateAnswerObjectBuilderTest {

    private TemplateAnswerObjectBuilder builder;
    private ExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        builder = new TemplateAnswerObjectBuilder();


        exceptionHandler = new ExceptionHandler() {
            @Override
            public Object handle(AnswerObject answerObject, Throwable t) {
                answerObject.put("errorCode", "-1");
                answerObject.put("message", "失败");
                answerObject.put("status", "failure");
                return new SkipNext(); // 不做任何处理
            }
        };
    }

    @Test
    void build() {
        TemplateAnswerObjectBuilder builder = new TemplateAnswerObjectBuilder();
        builder.property("abc", "234")
                .templateProperty("tm", 5);

        String s = JSONArray.toJSONString(builder.build());
        Object o = JSONArray.toJSON(builder.build().getProperties().values());
        System.out.println(s);
        System.out.println(o.getClass() + ":\n" + o);
    }


    @Test
    void templateProperty() {
        assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() {
                builder.templateProperty("errorCode", "0");
                builder.templateProperty("message", "成功");
                builder.templateProperty("status", "success");
                builder.canModifyTemplateProperty(true);
                AnswerObject answerObject = builder.build();
                answerObject.put("result", new Computable() {
                    @Override
                    public Object compute() {
                        throw new RuntimeException();
                    }
                });
            }
        });
    }

    @Test
    void buildListAnswer() {
        AnswerObject o = builder.property("name", "1").buildListAnswer();

        JSONArray j = (JSONArray) JSONArray.toJSON(o);
        assertEquals(j.size(), 1);
    }

    @Test
    void buildMapAnswer() {
        AnswerObject o = builder.canModifyTemplateProperty(true)
                .property("c1", "c1")
                .templateProperty("t1", "t1")
                .buildMapAnswer();

        o.put("t1", "t2");
        o.put("c1", "c2");
        assertEquals("t2", o.get("t1"));
        assertEquals("c2", o.get("c1"));
    }
}