package com.leones.talentguide.model;

/**
 * Created by Diegormz on 05/04/2018.
 */

public class EventsInfo {

    private String nameEvent;
    private String time;
    private String zone;

    public EventsInfo(String nameEvent, String time, String zone) {
        this.nameEvent = nameEvent;
        this.time = time;
        this.zone = zone;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
