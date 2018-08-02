package com.ace.trade.common.exception;

/**
 * @author 赵建龙
 * @date 2018/8/2
 */
public class AceMQException extends Exception {

    private static final long serialVersionUID = -8007998245336817227L;

    public AceMQException() {
    }

    public AceMQException(String message) {
        super(message);
    }

    public AceMQException(String message, Throwable cause) {
        super(message, cause);
    }

    public AceMQException(Throwable cause) {
        super(cause);
    }
}
