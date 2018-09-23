package no.sysco.middleware.disasterslackbot.model;


public class VehicleDelocalized {

    private String type;
    private String carId;
    private String deviceId;
    private String carName;
    private Integer lastKnownTrack;
    private String raceStatus;
    private Integer raceId;
    private Integer lap;
    private Long dateTime;
    private String dateTimeString;
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
