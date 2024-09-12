package com.sparta.auth.domain.model;

public enum UserRole {
    MASTER(Authority.MASTER),
    HUB_MANAGER(Authority.HUB_MANAGER),
    DELIVERY_MANAGER(Authority.DELIVERY_MANAGER),
    VENDOR_MANAGER(Authority.VENDOR_MANAGER);

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority(){
        return this.authority;
    }

    public static class Authority{
        public static final String MASTER="MASTER";
        public static final String HUB_MANAGER="HUB_MANAGER";
        public static final String DELIVERY_MANAGER="DELIVERY_MANAGER";
        public static final String VENDOR_MANAGER="VENDOR_MANAGER";

    }
}
