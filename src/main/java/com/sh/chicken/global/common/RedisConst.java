package com.sh.chicken.global.common;

public enum RedisConst {

    MAIN("main"),
    MENU("menu:"),
    LIKE("menu:like:");
    private final String prefix;

    RedisConst(String prefix) {
        this.prefix = prefix;
    }

    public String prefix() {
        return prefix;
    }
}
