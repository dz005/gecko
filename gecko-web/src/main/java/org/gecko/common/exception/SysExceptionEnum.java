package org.gecko.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统异常枚举
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Getter
@AllArgsConstructor
public enum SysExceptionEnum {

    /**
     * 其他
     */
    WRITE_ERROR(500, "渲染界面错误"),

    /**
     * 文件上传
     */
    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),
    FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"),

    /**
     * 错误的请求
     */
    REQUEST_NULL(400, "请求有错误"),
    SERVER_ERROR(500, "服务器异常");

    private int code;
    private String message;
    private String urlPath;

    SysExceptionEnum(int code, String message) {
        this(code, message, null);
    }

}
