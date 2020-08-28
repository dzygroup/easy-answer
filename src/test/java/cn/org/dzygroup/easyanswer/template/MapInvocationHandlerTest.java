package cn.org.dzygroup.easyanswer.template;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author 戴志勇
 */
class MapInvocationHandlerTest {

    @Test
    void proxy() {
        TemplateAnswerObjectBuilder builder = new TemplateAnswerObjectBuilder();
        builder.templateProperty("t1", "t1");
        MapInvocationHandler handler = new MapInvocationHandler(builder.build());
        Object o = Proxy.newProxyInstance(MapInvocationHandlerTest.class.getClassLoader(), new Class[]{Map.class, AnswerObject.class}, handler);
        System.out.println(o);
        System.out.println(JSONObject.toJSONString(o));

        AnswerObject a = (AnswerObject) o;
        a.clear();
        a.getProperties();
    }
}