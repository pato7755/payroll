package com.taryafrica.payroll.utils;

public enum ResponseCodes {
    SUCCESS(100),
    FAILURE(101),
    ERROR(103);

    private final int statusCode;

    ResponseCodes(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
