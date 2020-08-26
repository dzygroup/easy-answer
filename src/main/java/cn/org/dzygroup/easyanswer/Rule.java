package cn.org.dzygroup.easyanswer;

/**
 * @author 戴志勇
 */
public interface Rule {
    /**
     * 匹配规则，每个方法执行过程产生的结果和异常都会用来作为参数传递
     *
     * @param result    执行结果
     * @param throwable 执行异常
     * @return true匹配，false不匹配
     */
    boolean matches(Object result, Throwable throwable);
}
