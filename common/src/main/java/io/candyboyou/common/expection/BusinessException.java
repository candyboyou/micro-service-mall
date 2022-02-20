package io.candyboyou.common.expection;

import io.swagger.annotations.ApiModelProperty;

public class BusinessException extends RuntimeException {

    @ApiModelProperty(notes = "用来弹窗展示的错误信息，如果不为空，展示给用户")
    private String alertMsg;

    private int errorCode = 999;

    public BusinessException(int errorCode, String alertMsg) {
        super(alertMsg);
        this.errorCode = errorCode;
        this.alertMsg = alertMsg;
    }

    public BusinessException(int errorCode, String alertMsg, String errMsg) {
        super(errMsg);
        this.errorCode = errorCode;
        this.alertMsg = alertMsg;
    }

    public BusinessException(String alertMsg) {
        super(alertMsg);
        this.alertMsg = alertMsg;
    }

    public BusinessException(String alertMsg, String errMsg) {
        super(errMsg);
        this.alertMsg = alertMsg;
    }

    public BusinessException(String alertMsg, String errMsg, Throwable e) {
        super(errMsg, e);
        this.alertMsg = alertMsg;
    }

    public BusinessException(String alertMsg, Throwable e) {
        super(alertMsg, e);
        this.alertMsg = alertMsg;
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public String getAlertMsg() {
        return this.alertMsg;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
