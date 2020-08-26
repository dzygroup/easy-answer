package cn.org.dzygroup.easyanswer;

/**
 * @author 戴志勇
 */
public class EasyAnswer {

    public static CommonAnswerBuilder commonAnswer(Computable<?> computable) {
        return new CommonAnswerBuilder(computable);
    }

    public static AnswerBuilder answer() {
        return new AnswerBuilder();
    }


}
