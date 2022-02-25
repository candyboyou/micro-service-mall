package io.candyboyou.common.framework.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serial;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result implements Serializable {

    @Serial
    private static final long serialVersionUID = 7849644702830940934L;

    private Integer code = 200;
    private String message = "operate succeed";
    private Object data;

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object data) {
        Result result = new Result();
        result.setData(data);
        return result;
    }

    public static Result error(int code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(500);
        result.setMessage("operate error");
        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
