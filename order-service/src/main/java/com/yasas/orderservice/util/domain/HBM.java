package com.yasas.orderservice.util.domain;

public class HBM {

    public static final HBM INSTANCE = new HBM();

    private final String _type = "HB";

    public String get_type() {
        return _type;
    }
}

