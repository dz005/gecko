package org.gecko.core.aop;

import org.gecko.common.exception.BizException;
import org.gecko.common.exception.SysException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author: dengzhi
 * @date: 2018/5/31
 */
public class GlobalExceptionHandler {

    public static final String CODE = "code";
    public static final String MESSAGE = "message";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handle(Exception ex, HttpServletRequest request) {
        if (isAjaxRequest(request)) {
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            Map attributes = new HashMap();
            attributes.put(CODE, -1);
            attributes.put(MESSAGE, ex.getMessage());
            view.setAttributesMap(attributes);
            return new ModelAndView(view);
        } else {
            request.setAttribute(CODE, -1);
            request.setAttribute(MESSAGE, ex.getMessage());
            return new ModelAndView("/page/error");
        }
    }

    @ExceptionHandler({BizException.class, SysException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handle(SysException ex, HttpServletRequest request) {
        if (isAjaxRequest(request)) {
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            Map attributes = new HashMap();
            attributes.put(CODE, ex.getCode());
            attributes.put(MESSAGE, ex.getMessage());
            view.setAttributesMap(attributes);
            return new ModelAndView(view);
        } else {
            request.setAttribute(CODE, ex.getCode());
            request.setAttribute(MESSAGE, ex.getMessage());
            String viewName = StringUtils.hasText(ex.getUrlPath()) ? ex.getUrlPath() : "/page/error";
            return new ModelAndView(viewName);
        }
    }


    /**
     * 判断是否是Ajax请求
     *
     * @param request
     * @return
     */
    protected static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }

}
