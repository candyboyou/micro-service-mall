package io.candyboyou.common.framework.mvc.handler;

public enum GlobalResponseCodeEnum {

    SUCCESS(200),
    FAIL(900, "操作失败"),
    EXCEPTION(999, "系统异常");

    private int code;

    private String desc;

    private GlobalResponseCodeEnum(int code)
    {
        this.code = code;
    }

    private GlobalResponseCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode()
    {
        return this.code;
    }

    public String getDesc()
    {
        return this.desc;
    }

}
