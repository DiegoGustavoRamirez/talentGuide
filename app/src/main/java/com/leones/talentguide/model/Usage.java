package com.leones.talentguide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Diegormz on 04/04/2018.
 */

public class Usage {


    @SerializedName("sent")
    @Expose
    private Integer sent;
    @SerializedName("recv")
    @Expose
    private Integer recv;


}
