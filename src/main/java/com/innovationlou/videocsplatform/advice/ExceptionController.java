package com.innovationlou.videocsplatform.advice;

import com.innovationlou.videocsplatform.exception.UnauthorizedException;
import com.innovationlou.videocsplatform.util.ControllerUtil;
import com.innovationlou.videocsplatform.vo.JsonResult;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {
    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public JsonResult handle401(ShiroException e) {
        return ControllerUtil.customResult(401,e.getMessage(),null);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public JsonResult handle401() {
        return ControllerUtil.customResult(401,"未认证",null);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult globalException(HttpServletRequest request, Throwable ex) {
        return ControllerUtil.customResult(getStatus(request).value(),ex.getMessage(),null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
