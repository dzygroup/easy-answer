package cn.org.dzygroup.easyanswer.template;

/**
 * @author 戴志勇
 */
public class InterfaceNotImplementException extends RuntimeException {

    public InterfaceNotImplementException() {
    }

    public InterfaceNotImplementException(String message) {
        super(message);
    }

    public InterfaceNotImplementException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterfaceNotImplementException(Throwable cause) {
        super(cause);
    }

    public InterfaceNotImplementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
