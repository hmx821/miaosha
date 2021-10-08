package com.hmx.miaosha.exception;

import com.hmx.miaosha.result.CodeMsg;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-07 17:50
 **/
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg cm) {
        this.codeMsg = cm;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
