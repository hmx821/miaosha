package com.hmx.miaosha.result;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-04 17:32
 **/
public enum CodeMsg {

    //通用异常
    SUCCESS(0,"success"),
    SERVER_ERROR(500100, "服务端异常"),
    BIND_ERROR(500101,"参数校验异常:%s"),
    //登录模块 5002XX
    SESSION_ERROR(500210, "Session不存在或者已经失效"),
    PASSWORD_BLANK(500211, "登录密码不能为空"),
    MOBILE_BLANK(500212, "手机号不能为空"),
    MOBILE_ERROR(500213, "手机号格式错误"),
    MOBILE_NOT_EXIST(500214, "手机号不存在"),
    PASSWORD_ERROR(500215, "密码错误"),
    TOKEN_ERROR(500216, "token错误"),
    //商品模块 5003XX

    //订单模块 5004XX

    //秒杀模块 5005XX
    MIAO_SHA_OVER(500500, "商品已经秒杀完毕"),
    REPEAT_MIAOSHA(500501, "不能重复秒杀");

    private int code;

    private String msg;

    public CodeMsg fillArgs(Object... args) {
        //将信息填入占位符%s
        String message = String.format(this.msg, args);
        this.setMsg(message);
        return this;
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
