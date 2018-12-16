package com.tripper.nfctripper.aqua.model.testModel;

import com.google.gson.annotations.SerializedName;
import com.tripper.nfctripper.aqua.model.Data;
import com.tripper.nfctripper.aqua.model.testModel.Test;

import java.util.ArrayList;

public class DataTest extends Data {

    @SerializedName("data")
    private ArrayList<Test> testList;


    public ArrayList<Test> getTestList() {
        return testList;
    }

    public void setTestList(ArrayList<Test> testList) {
        this.testList = testList;
    }
}
