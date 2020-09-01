package cn.org.dzygroup.easyanswer.template;

/**
 * @author 戴志勇
 */
public enum ErrorType {

    ERROR_001("001", "错误001"),
    ERROR_002("002", "错误002");

    private String code;
    private String message;

    ErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
