package com.sh.chicken.global;

public enum SessionConst {
    COMMON_USER("user");
    private String rule;

    SessionConst(String rule) {
        this.rule = rule;
    }

    public String getRule(){
        return rule;
    }
}

