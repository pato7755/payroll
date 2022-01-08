package com.metadata.schoolreg;

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
