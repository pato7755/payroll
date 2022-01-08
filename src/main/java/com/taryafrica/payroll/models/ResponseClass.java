package com.metadata.schoolreg.models;

import com.metadata.schoolreg.ResponseCodes;

import java.util.Collections;
import java.util.List;

public class ResponseClass {

    private int responseCode;
    private String responseMessage;
    private Object data;

    public ResponseClass() {
    }

    public ResponseClass(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public ResponseClass(int responseCode, String responseMessage, Object data) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
