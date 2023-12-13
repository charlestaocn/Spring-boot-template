package com.charles.template.controller;

import com.charles.template.entity.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author charles tao
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<Object> handlerRuntime(Throwable throwable) {
        log.error(" error handler", throwable);
        return BaseResponse.fail().msg(throwable.getMessage()).build();
    }

}
