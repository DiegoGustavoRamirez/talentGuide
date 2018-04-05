package com.leones.talentguide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Diegormz on 04/04/2018.
 */

public class Devices {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("mac")
    @Expose
    private String mac;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }


}
