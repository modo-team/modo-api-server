package com.modo.modoapiserver.enums;

public enum OrderValue {
    NEWEST("newest"), POPULAR("popular");

    private String value;

    OrderValue(String value) {
        this.value = value;
    }
}
