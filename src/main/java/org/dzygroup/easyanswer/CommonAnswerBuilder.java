package org.dzygroup.easyanswer;

import java.util.Map;

/**
 * @author 戴志勇
 */
public class CommonAnswerBuilder {

    private final RuleMatcher ruleMatcher;
    private Computable<?> computable;
    private ExceptionCallback exceptionCallback;
    private FinishCallback finishCallback;

    public CommonAnswerBuilder(Computable<?> computable) {
        this.computable = computable;
        ruleMatcher = new RuleMatcher();
        ruleMatcher.setRule(new Rule() {
            @Override
            public boolean matches(Object result, Throwable throwable) {
                return true;
            }
        });
        ruleMatcher.addRuleAction(new RuleAction() {
            @Override
            public Object action(Object result, Throwable throwable, Map<String, Object> context) {
                if (throwable != null) {
                    if (exceptionCallback != null) {
                        try {
                            return exceptionCallback.execute(throwable);
                        } catch (Throwable t) {
                            throw new RuntimeException("异常处理器发出异常", t);
                        }
                    }
                } else {
                    try {
                        return finishCallback.execute(result);
                    } catch (Throwable t) {
                        throw new RuntimeException("完成后处理器错误", t);
                    }
                }
                return result;
            }
        });
    }


    private CommonAnswerBuilder compute(Computable<?> computable) {
        this.computable = computable;
        return this;
    }


    public CommonAnswerBuilder onException(ExceptionCallback callback) {
        this.exceptionCallback = callback;
        return this;
    }

    public CommonAnswerBuilder onFinish(FinishCallback callback) {
        this.finishCallback = callback;
        return this;
    }

    public <R> R doAnswer() {
        return new AnswerBuilder().compute(computable).matcher(ruleMatcher).doAnswer();
    }
}
