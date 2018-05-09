package com.startcaft.basic.controller;

import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.vo.ResponseBean;
import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理组件
 * @author startcaft
 * @date 2018/3/5
 */
@RestControllerAdvice
public class ExceptionController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseBean handle401(ShiroException e) {
        return new ResponseBean(false, e.getMessage(), ERROR_MSG + "_401");
    }

    /**
     * 捕获业务异常，业务异常就正常返回
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BasicProException.class)
    public ResponseBean handle401(BasicProException e) {
        return new ResponseBean(false, e.getMessage(), ERROR_MSG);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean globalException(HttpServletRequest request, Throwable ex) {
        LOGGER.error(ex.getMessage(),ex);
        return new ResponseBean(false, ex.getMessage(), ERROR_MSG + "_" + getStatus(request).value());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
