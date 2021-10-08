package com.hmx.miaosha.result;

import lombok.Data;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-04 17:24
 **/
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg = CodeMsg.SUCCESS.getMsg();
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    /**
     * 成功时候调用
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 失败时候调用
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(CodeMsg cm) {
        return new Result<T>(cm);
    }
}
