package cn.org.dzygroup.easyanswer;

/**
 * @author 戴志勇
 */
public class ComputeResultPair {

    private Object result;
    private Throwable throwable;

    public ComputeResultPair(Object result, Throwable throwable) {
        this.result = result;
        this.throwable = throwable;
    }

    public ComputeResultPair() {
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
