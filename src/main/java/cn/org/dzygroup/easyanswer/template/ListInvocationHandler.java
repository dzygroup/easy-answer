package cn.org.dzygroup.easyanswer.template;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author 戴志勇
 */
public class ListInvocationHandler implements InvocationHandler {

    private final AnswerObject target;

    public ListInvocationHandler(AnswerObject target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().isInstance(target)) {
            return method.invoke(target, args);
        }
        return method.invoke(new ArrayList<Object>(target.getProperties().values()), args);
    }
}
