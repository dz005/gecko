package org.gecko.common.exception;

/**
 * 业务异常
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
public class BizException extends SysException {

    public BizException(int code, String message, String urlPath) {
        super(code, message, urlPath);
    }

    public BizException(int code, String message) {
        super(code, message);
    }

    public BizException(BizExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMessage(), exceptionEnum.getUrlPath());
    }

}
