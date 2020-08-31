package cn.org.dzygroup.easyanswer.template;

/**
 * @author 戴志勇
 */
public final class NoValue {

    private final String message;

    public NoValue(String message) {
        this.message = message;
    }

    public NoValue() {
        message = "没有返回任何值";
    }

    @Override
    public String toString() {
        return message;
    }
}
