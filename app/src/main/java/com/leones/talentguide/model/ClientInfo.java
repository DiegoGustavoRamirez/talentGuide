package com.leones.talentguide.model;

/**
 * Created by Diegormz on 05/04/2018.
 */

public class ClientInfo {

    private String clientname;
    private String timeConect;

    public ClientInfo(String clientname, String timeConect) {
        this.clientname = clientname;
        this.timeConect = timeConect;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getTimeConect() {
        return timeConect;
    }

    public void setTimeConect(String timeConect) {
        this.timeConect = timeConect;
    }
}
