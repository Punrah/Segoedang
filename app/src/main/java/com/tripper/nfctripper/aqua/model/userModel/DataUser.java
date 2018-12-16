package com.tripper.nfctripper.aqua.model.userModel;

import com.google.gson.annotations.SerializedName;
import com.tripper.nfctripper.aqua.model.Data;

import java.util.ArrayList;

public class DataUser extends Data {

    @SerializedName("data")
    private ArrayList<User> userList;


    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
}
