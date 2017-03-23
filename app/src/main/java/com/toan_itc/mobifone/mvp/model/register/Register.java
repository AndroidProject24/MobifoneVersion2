package com.toan_itc.mobifone.mvp.model.register;

public class Register {

    /**
     * error : 0
     * reason : success register
     */

    private int error;
    private String reason;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
