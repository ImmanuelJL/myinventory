package com.soft_gain.myinventory.Model.Barang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleDataBarang {
    @SerializedName("success")
    @Expose
    private SingleDataSuccess success;

    public SingleDataSuccess getSuccess() {
        return success;
    }

    public void SingleDataSuccess(SingleDataSuccess success) {
        this.success = success;
    }
}
