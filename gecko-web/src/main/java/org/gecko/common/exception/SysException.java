package org.gecko.common.exception;

import lombok.Getter;

/**
 * 系统异常
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Getter
public class SysException extends RuntimeException {

    /**
     * code码
     */
    private int code;
    /**
     * 业务异常跳转的页面
     */
    private String urlPath;


    public SysException(int code, String message, String urlPath) {
        super(message);
        this.code = code;
        this.urlPath = urlPath;
    }

    public SysException(int code, String message) {
        this(code, message, null);
    }

    public SysException(SysExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMessage(), exceptionEnum.getUrlPath());
    }

}
