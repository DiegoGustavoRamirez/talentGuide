package com.leones.talentguide.model;

/**
 * Created by Diegormz on 04/04/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MacAddress {

    @SerializedName("macAddress")
    @Expose
    private String macAddress;


    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

}
