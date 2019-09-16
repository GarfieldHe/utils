package com.utils.work.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice // 全局捕获异常类，只要作用在@RequestMapping上，所有的异常都会被捕获
public class GlobalExceptionHandler {
    private final String TAG = "GlobalExceptionHandler";
    Logger log = LoggerFactory.getLogger(this.getClass());

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({GlobalException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public GlobalExceptionDTO globalException(GlobalException e) throws Exception {
        this.log.warn("GlobalExceptionHandler", e.getPath());
        this.log.warn("GlobalExceptionHandler", e);
        return new GlobalExceptionDTO(e);
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GlobalExceptionDTO errorHandler(Exception e) throws Exception {
        this.log.error("GlobalExceptionHandler", e);
        GlobalExceptionDTO r = new GlobalExceptionDTO();
        r.setCode(500);
        r.setMessage("Internal Error: " + e.getMessage());
        return r;
    }
}
