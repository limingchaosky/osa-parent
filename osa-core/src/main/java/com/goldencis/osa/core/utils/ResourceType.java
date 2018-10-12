package com.goldencis.osa.core.utils;

/**
 * Created by limingchao on 2018/10/12.
 */
public enum ResourceType {

    NAVIGATION("Navigation", 1), OPERATION("Operation", 2);

    private String name;

    private Integer value;

    ResourceType(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
