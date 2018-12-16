package com.tripper.nfctripper.aqua.model.userModel;

import com.google.gson.annotations.SerializedName;
import com.tripper.nfctripper.aqua.model.Result;

public class ResultUser extends Result {

    @SerializedName("data")
    private DataUser dataUser;


    public DataUser getDataUser() {
        return dataUser;
    }

    public void setDataUser(DataUser dataUser) {
        this.dataUser = dataUser;
    }
}
