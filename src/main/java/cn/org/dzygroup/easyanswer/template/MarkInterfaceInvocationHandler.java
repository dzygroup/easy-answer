package cn.org.dzygroup.easyanswer.template;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 戴志勇
 */
class MarkInterfaceInvocationHandler implements InvocationHandler {

    private AnswerObject target; // 代理目标
    private InvocationHandler otherHandler; // 其他接口
    private boolean buildList;
    private boolean buildMap;

    public MarkInterfaceInvocationHandler() {
    }

    public MarkInterfaceInvocationHandler(AnswerObject target) {
        this(target, null);
    }

    /**
     * @param target       代理目标
     * @param otherHandler 用户定义的其他接口实现，不为空，只执行该实现
     */
    public MarkInterfaceInvocationHandler(AnswerObject target, InvocationHandler otherHandler) {
        this.target = target;
        this.otherHandler = otherHandler;
    }

    public MarkInterfaceInvocationHandler setTarget(AnswerObject target) {
        this.target = target;
        return this;
    }

    public MarkInterfaceInvocationHandler setOtherHandler(InvocationHandler otherHandler) {
        this.otherHandler = otherHandler;
        return this;
    }

    public MarkInterfaceInvocationHandler setBuildList(boolean buildList) {
        this.buildList = buildList;
        return this;
    }

    public MarkInterfaceInvocationHandler setBuildMap(boolean buildMap) {
        this.buildMap = buildMap;
        return this;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().isInstance(target)) {
            return method.invoke(target, args);
        }

        if (otherHandler != null) {
            return otherHandler.invoke(proxy, method, args);
        }


        if (buildList && method.getDeclaringClass() == List.class) {
            return method.invoke(new ArrayList<Object>(target.getProperties().values()), args);
        }

        if (buildMap && method.getDeclaringClass() == Map.class) {
            return method.invoke(target.getProperties(), args);
        }

//        return new NoValue("没有实现接口" + method.getDeclaringClass());
        throw new InterfaceNotImplementException("没有实现接口：" + method.getDeclaringClass());
    }
}
