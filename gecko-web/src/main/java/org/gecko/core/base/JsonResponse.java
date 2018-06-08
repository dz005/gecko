package org.gecko.core.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AJAX 响应结果
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
@Getter
@AllArgsConstructor
public class JsonResponse {

    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = -1;
    public static final JsonResponse SUCCESS = new JsonResponse(SUCCESS_CODE, null, null);
    public static final JsonResponse ERROR = new JsonResponse(ERROR_CODE, "操作失败", null);

    /***
     * 响应编码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public static JsonResponse buildSuccess(Object data) {
        return new JsonResponse(SUCCESS_CODE, null, data);
    }

    public static JsonResponse buildError(int code, String message) {
        return new JsonResponse(code, message, null);
    }

    public static JsonResponse buildError(String message) {
        return buildError(ERROR_CODE, message);
    }

}
