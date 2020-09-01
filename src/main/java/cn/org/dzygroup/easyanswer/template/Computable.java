package cn.org.dzygroup.easyanswer.template;

/**
 * 实现一个计算过程，返回一个计算结果
 *
 * @author 戴志勇
 */
public interface Computable {

    /**
     * 实现计算过程
     *
     * @param oldValue 旧值
     * @return 返回新值
     */
    Object compute(Object oldValue);
}
