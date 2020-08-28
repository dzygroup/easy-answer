package cn.org.dzygroup.easyanswer.template.res;

/**
 * @author 戴志勇
 */
public class APITemplate {

    private String msg; // 消息
    private Object data; // 该次响应数据
    private Object extra; // 全局数据
    private int code; // 响应代码

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
