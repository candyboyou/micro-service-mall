package io.candyboyou.common.framework.mvc.handler;

import com.google.common.base.Throwables;
import io.candyboyou.common.expection.BusinessException;
import io.candyboyou.common.utils.TraceIdUtils;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class GlobalResponse<T> {

    @ApiModelProperty(notes = "业务成功时:true,失败时：false")
    protected boolean success = true;

    @ApiModelProperty(notes = "可以用来指定特定的错误原因")
    private Integer errorCode;

    @ApiModelProperty(notes = "数据")
    private T data;

    @ApiModelProperty(notes = "不为空。等于200时表示业务成功，其他表示业务失败")
    private Integer status = 200;

    @ApiModelProperty(notes = "用来弹窗展示的错误信息，如果不为空，展示给用户")
    private String alertMsg;

    @ApiModelProperty(notes = "系统错误信息，供开发人员问题跟踪使用")
    private String errorMsg;

    @ApiModelProperty(notes = "日志跟踪号")
    protected String traceLogId;

    @ApiModelProperty(notes = "额外信息")
    private Map<String, String> extMap;

    public GlobalResponse() {
    }

    public GlobalResponse(T data, String traceLogId) {
        this.traceLogId = traceLogId;
        this.data = data;
        this.alertMsg = "操作成功";
    }

    public static <T> GlobalResponse<T> success(T data) {
        return new GlobalResponse<>(data, TraceIdUtils.getTraceId());
    }

    public static <T> GlobalResponse<T> fail(String alertMsg) {
        GlobalResponse<T> resp = new GlobalResponse<>();
        resp.setStatusCode(GlobalResponseCodeEnum.FAIL.getCode());
        resp.setTraceLogId(TraceIdUtils.getTraceId());
        resp.setAlertMsg(StringUtils.isEmpty(alertMsg) ? GlobalResponseCodeEnum.FAIL.getDesc() : alertMsg);
        resp.setErrorCode(4000);
        resp.setSuccess(false);
        resp.setErrorMsg(GlobalResponseCodeEnum.FAIL.getDesc());
        return resp;
    }

    /**
     * 同时返回异常
     */
    public static <T> GlobalResponse<T> exception(Throwable e, String alertMsg) {
        GlobalResponse<T> resp = new GlobalResponse<>();
        resp.setStatusCode(GlobalResponseCodeEnum.EXCEPTION.getCode());
        resp.setTraceLogId(TraceIdUtils.getTraceId());
        resp.setAlertMsg(StringUtils.isEmpty(alertMsg) ? GlobalResponseCodeEnum.EXCEPTION.getDesc() : alertMsg);
        resp.setErrorCode(4000);
        resp.setSuccess(false);
        resp.setErrorMsg(Throwables.getStackTraceAsString(Throwables.getRootCause(e)));
        return resp;
    }

    /**
     * 业务异常
     * @param e
     * @param <T>
     * @return
     */
    public static <T> GlobalResponse<T> businessFail(BusinessException e) {
        GlobalResponse<T> resp = new GlobalResponse<>();
        resp.setStatusCode(GlobalResponseCodeEnum.EXCEPTION.getCode());
        resp.setErrorCode(e.getErrorCode());
        resp.setTraceLogId(TraceIdUtils.getTraceId());
        resp.setAlertMsg(StringUtils.isEmpty(e.getAlertMsg()) ? GlobalResponseCodeEnum.EXCEPTION.getDesc() : e.getAlertMsg());
        resp.setErrorMsg(e.getMessage());
        resp.setSuccess(false);
        return resp;
    }

    public static <T> GlobalResponse<T> httpError(Integer httpCode) {
        int code = null == httpCode ? GlobalResponseCodeEnum.EXCEPTION.getCode() : httpCode;
        GlobalResponse<T> resp = new GlobalResponse<>();
        resp.setStatusCode(code);
        resp.setTraceLogId(TraceIdUtils.getTraceId());
        resp.setAlertMsg(GlobalResponseCodeEnum.EXCEPTION.getDesc());
        resp.setSuccess(false);
        resp.setErrorMsg("http请求异常，Http statusCode = " + httpCode);
        return resp;
    }

    public boolean isSuccess()
    {
        return this.success;
    }

    public Integer getErrorCode()
    {
        return this.errorCode;
    }

    public T getData()
    {
        return this.data;
    }

    public Integer getStatusCode()
    {
        return this.status;
    }

    public String getAlertMsg()
    {
        return this.alertMsg;
    }

    public String getErrorMsg()
    {
        return this.errorMsg;
    }

    public String getTraceLogId()
    {
        return this.traceLogId;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public void setErrorCode(Integer errorCode)
    {
        this.errorCode = errorCode;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public void setStatusCode(int statusCode)
    {
        this.status = statusCode;
    }

    public void setAlertMsg(String alertMsg)
    {
        this.alertMsg = alertMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public void setTraceLogId(String traceLogId)
    {
        this.traceLogId = traceLogId;
    }

    public Map<String, String> getExtMap()
    {
        return extMap;
    }

    public void setExtMap(Map<String, String> extMap)
    {
        this.extMap = extMap;
    }

}
