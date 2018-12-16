package com.tripper.nfctripper.aqua.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Belal on 14/04/17.
 */

public class Result {
    @SerializedName("success")
    private Boolean success;

    @SerializedName("message")
    private String message;


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}