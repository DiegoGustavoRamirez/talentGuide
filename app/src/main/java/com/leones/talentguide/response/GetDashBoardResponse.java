package com.leones.talentguide.response;

/**
 * Created by Diegormz on 04/04/2018.
 */

import com.leones.talentguide.model.Devices;
import com.leones.talentguide.model.DevicesList;


public class GetDashBoardResponse {

    private Devices devices;

    public Devices getDevices() {
        return devices;
    }

    public void setDevices(Devices devices) {
        this.devices = devices;
    }
}

