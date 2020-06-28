package com.soft_gain.myinventory.Model.Barang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleDataSuccess {
    @SerializedName("data")
    @Expose
    private Datum data;

    public Datum getData() {
        return data;
    }

    public void setData(Datum data) {
        this.data = data;
    }
}
