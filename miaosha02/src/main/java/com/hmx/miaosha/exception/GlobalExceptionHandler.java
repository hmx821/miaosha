package com.hmx.miaosha.exception;

import com.hmx.miaosha.result.CodeMsg;
import com.hmx.miaosha.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-05 23:20
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return Result.fail(ex.getCodeMsg());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> erros = ex.getAllErrors();
            ObjectError error = erros.get(0);
            String msg = error.getDefaultMessage();
            return Result.fail(CodeMsg.BIND_ERROR.fillArgs(msg));
        }
        return Result.fail(CodeMsg.SERVER_ERROR);
    }

}
