package com.tripper.nfctripper.aqua.model.testModel;

import com.google.gson.annotations.SerializedName;
import com.tripper.nfctripper.aqua.model.Result;
import com.tripper.nfctripper.aqua.model.userModel.DataUser;

public class ResultTest extends Result {

    @SerializedName("data")
    private DataTest dataTest;

    public DataTest getDataTest() {
        return dataTest;
    }

    public void setDataTest(DataTest dataTest) {
        this.dataTest = dataTest;
    }
}
