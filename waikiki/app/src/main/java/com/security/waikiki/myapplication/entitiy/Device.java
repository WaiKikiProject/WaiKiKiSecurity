package com.security.waikiki.myapplication.entitiy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Device extends RealmObject {

    @Expose
    @SerializedName("device_id")
    @PrimaryKey
    private String DeviceID;

    @Expose
    @SerializedName("device_name")
    private String DeviceName;

    @Expose
    @SerializedName("master")
    private String Master;

    @Expose
    @SerializedName("s_mode")
    private String SMode;

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public String getMaster() {
        return Master;
    }

    public void setMaster(String master) {
        Master = master;
    }

    public String getSMode() {
        return SMode;
    }

    public void setSMode(String SMode) {
        this.SMode = SMode;
    }
}
