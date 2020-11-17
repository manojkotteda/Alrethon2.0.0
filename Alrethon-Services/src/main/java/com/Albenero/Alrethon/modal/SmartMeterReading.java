package com.Albenero.Alrethon.modal;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "smart_meter_reading")
public class SmartMeterReading implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String readingId;
    @Column
    private String deviceId;
    @Column
    private Double energyConsumed;
    @Column
    private Date timeStamp;
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @Column
    private Double deviceBattery;



    public SmartMeterReading() {
    }

    @Override
    public String toString() {
        return "SmartMeterReading{" +
                "readingId='" + readingId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", energyConsumed=" + energyConsumed +
                ", timeStamp=" + timeStamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", deviceBattery=" + deviceBattery +
                '}';
    }

    public String getReadingId() {
        return readingId;
    }

    public void setReadingId(String readingId) {
        this.readingId = readingId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Double getEnergyConsumed() {
        return energyConsumed;
    }

    public void setEnergyConsumed(Double energyConsumed) {
        this.energyConsumed = energyConsumed;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getDeviceBattery() {
        return deviceBattery;
    }

    public void setDeviceBattery(Double deviceBattery) {
        this.deviceBattery = deviceBattery;
    }


}
