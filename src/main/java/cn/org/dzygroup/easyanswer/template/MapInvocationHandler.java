package cn.org.dzygroup.easyanswer.template;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author 戴志勇
 */
public class MapInvocationHandler implements InvocationHandler {


    private final AnswerObject target;

    public MapInvocationHandler(AnswerObject target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass() == Map.class) {
            return method.invoke(target.getProperties(), args);
        } else if (method.getDeclaringClass().isInstance(target)) {
            return method.invoke(target, args);
        }
        throw new UnknownInterfaceException("未知的接口");
    }
}
