package cn.org.dzygroup.easyanswer.template;

import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author 戴志勇
 */
class AnswerObjectBuilderTest {

    private AnswerObjectBuilder builder;
    private ExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        builder = new AnswerObjectBuilder();
        exceptionHandler = new ExceptionHandler() {
            @Override
            public Object handle(AnswerObject answerObject, Throwable t) {
                answerObject.put("errorCode", "-1");
                answerObject.put("message", "失败");
                answerObject.put("status", "failure");
                return new SkipValue(); // 不做任何处理
            }
        };
    }

    @Test
    void build() {
        AnswerObjectBuilder builder = new AnswerObjectBuilder();
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
}