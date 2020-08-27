package cn.org.dzygroup.easyanswer.template;

/**
 * @author 戴志勇
 */
public class UnmodifiableValueException extends RuntimeException {
    public UnmodifiableValueException() {
    }

    public UnmodifiableValueException(String message) {
        super(message);
    }

    public UnmodifiableValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnmodifiableValueException(Throwable cause) {
        super(cause);
    }

    public UnmodifiableValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
