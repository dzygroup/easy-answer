package cn.org.dzygroup.easyanswer.template;

/**
 * @author 戴志勇
 */
public class UnknownInterfaceException extends RuntimeException {

    public UnknownInterfaceException() {
    }

    public UnknownInterfaceException(String message) {
        super(message);
    }

    public UnknownInterfaceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownInterfaceException(Throwable cause) {
        super(cause);
    }


}
