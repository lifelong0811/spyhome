package com.jason.spy.financial.constant;

public enum  BaseResponseEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数错误"),
    SYSTEM_EXCEPTION(2, "系统错误"),
    OUTTER_ERROR(3, "外部依赖返回失败"),
    ;

    private int code;
    private String message;

    private BaseResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
