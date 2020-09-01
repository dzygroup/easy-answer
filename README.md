# easy-answer

#### 介绍
一款简单的统一JSON API响应的小工具。
你可以在JSON API响应中设置通用的属性

#### 软件架构



#### 安装教程



#### 使用说明

```java
import cn.org.dzygroup.easyanswer.template.AnswerObject;import cn.org.dzygroup.easyanswer.template.ComputableExceptionHandler;import cn.org.dzygroup.easyanswer.template.TemplateAnswerObjectBuilder;

public class Test {

    public static void main(String[] args){
        TemplateAnswerObjectBuilder builder = new TemplateAnswerObjectBuilder();
        builder.templateProperty("t1", 1)
                .templateProperty("t2", 2)
                .templateProperty("t3", 3)
                .property("p1", "01")
                .property("p2", "03");
        builder.buildMapAnswer().set("1234", new ComputableExceptionHandler() {@Override public Object handle(AnswerObject answerObject, String name, Throwable t) {
            return "123";
        }});
    }
}
```


#### 参与贡献


```text
dzygroup
```

#### 码云特技

