package com.eric.base.exception;

/**
 * @Description
 * @Author eric
 * @Version V1.0.0
 * @Date 2019/4/30
 */
public class ServerErrException extends Exception {
    private String errMsg;

    public ServerErrException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public ServerErrException(Throwable cause) {
        super(cause);
    }
}
