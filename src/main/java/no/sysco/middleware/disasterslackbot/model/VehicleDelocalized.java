package no.sysco.middleware.disasterslackbot.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

// Prakhar : Dirty hack for column names. By default, the column names for stream are uppercase.
// ksql should be configured to fix this.
public class VehicleDelocalized {

    @JsonProperty("TYPE")
    private String type;
    @JsonProperty("CARID")
    private String carId;
    @JsonProperty("DEVICEID")
    private String deviceId;
    @JsonProperty("CARNAME")
    private String carName;
    @JsonProperty("LASTKNOWNTRACK")
    private Integer lastKnownTrack;
    @JsonProperty("RACESTATUS")
    private String raceStatus;
    @JsonProperty("RACEID")
    private Integer raceId;
    @JsonProperty("LAP")
    private Integer lap;
    @JsonProperty("DATETIME")
    private Long dateTime;
    @JsonProperty("DATETIMESTRING")
    private String dateTimeString;
    @JsonProperty("DEMOZONE")
    private String demozone;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getLastKnownTrack() {
        return lastKnownTrack;
    }

    public void setLastKnownTrack(Integer lastKnownTrack) {
        this.lastKnownTrack = lastKnownTrack;
    }

    public String getRaceStatus() {
        return raceStatus;
    }

    public void setRaceStatus(String raceStatus) {
        this.raceStatus = raceStatus;
    }

    public Integer getRaceId() {
        return raceId;
    }

    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }

    public Integer getLap() {
        return lap;
    }

    public void setLap(Integer lap) {
        this.lap = lap;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTimeString() {
        // if datetimeString is not available, then user this.datetime to populate values
        if(this.dateTimeString == null){
            return new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date(this.dateTime));
        }
        return dateTimeString;
    }

    public void setDateTimeString(String dateTimeString) {
        this.dateTimeString = dateTimeString;
    }

    public String getDemozone() {
        return demozone;
    }

    public void setDemozone(String demozone) {
        this.demozone = demozone;
    }
}
