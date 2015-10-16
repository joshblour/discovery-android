package com.yonahforst.discovery;

import android.bluetooth.le.ScanResult;

/**
 * Created by Yonah on 15/10/15.
 */
public class BLEUser {
    private ScanResult mScanRsult;
    private String mDeviceAddress;
    private String mUsername;
    private Boolean mIdentified;
    private Integer mRssi;
    private Integer mProximity;
    private long mUpdateTime;
    private EasedValue mEasedProximity;

    public BLEUser(ScanResult scanResult) {
        this.mScanRsult = scanResult;
        this.mDeviceAddress = mScanRsult.getDevice().getAddress();
        this.mRssi = mScanRsult.getRssi();
        this.mEasedProximity = new EasedValue();
    }

    public Integer convertRSSItoProximity(Integer rssi) {
        // eased value doesn't support negative values
        this.mEasedProximity.setValue(Math.abs(rssi) * 0.0f);//convert to float
        this.mEasedProximity.update();
        Integer proximity = Math.round(this.mEasedProximity.getValue() * -1.0f);
        return proximity;
    }
    public String getDeviceAddress() {
        return mDeviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.mDeviceAddress = deviceAddress;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public Boolean isIdentified() {
        return mIdentified;
    }

    public void setIdentified(Boolean mIdentified) {
        this.mIdentified = mIdentified;
    }

    public int getRssi() {
        return mRssi;
    }

    public void setRssi(int mRssi) {
        this.mRssi = mRssi;
        this.setProximity(convertRSSItoProximity(mRssi));
    }

    public Integer getProximity() {
        return mProximity;
    }

    public void setProximity(Integer mProximity) {
        this.mProximity = mProximity;
    }

    public long getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(long mUpdateTime) {
        this.mUpdateTime = mUpdateTime;
    }

}
