package com.hmx.miaosha.redis;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-05 14:12
 **/
public abstract class BasePrefix implements KeyPrefix{

    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    public BasePrefix() {
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    /**
     * 默认0代表永不过期
     * @return
     */
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        return getClass().getSimpleName() + ":" + prefix;
    }
}
