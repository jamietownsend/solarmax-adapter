package com.servebeer.please.solarmax;

import java.time.LocalDateTime;

public class PowerGenerationDetail {

    LocalDateTime dataAtDateTime;
    String address;

    // Rounded to 100 Wh
    Integer wattHoursToday;

    // Float dcVolts;
    // Float dcAmps;
    Float acVolts1;
    Float acAmps1;
    Float acVolts2;
    Float acAmps2;
    Float acVolts3;
    Float acAmps3;
    Integer acPowerWatts;
    Float acFrequencyHerz;
    Float acPowerPercent;
    Float temperatureHeatSink;
    Integer operationState;

    public LocalDateTime getDataAtDateTime() {
        return dataAtDateTime;
    }

    public PowerGenerationDetail dataAtDateTime(LocalDateTime dataAtDateTime) {
        this.dataAtDateTime = dataAtDateTime;
        return this;
    }

    public void setDataAtDateTime(LocalDateTime dataAtDateTime) {
        this.dataAtDateTime = dataAtDateTime;
    }

    public String getAddress() {
        return address;
    }

    public PowerGenerationDetail address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getWattHoursToday() {
        return wattHoursToday;
    }

    public PowerGenerationDetail wattHoursToday(Integer wattHoursToday) {
        this.wattHoursToday = wattHoursToday;
        return this;
    }

    public void setWattHoursToday(Integer wattHoursToday) {
        this.wattHoursToday = wattHoursToday;
    }

    // public Integer getDcVolts() {
    //     return dcVolts;
    // }

    // public PowerGenerationDetail dcVolts(Integer dcVolts) {
    //     this.dcVolts = dcVolts;
    //     return this;
    // }

    // public void setDcVolts(Integer dcVolts) {
    //     this.dcVolts = dcVolts;
    // }

    // public Float getDcAmps() {
    //     return dcAmps;
    // }

    // public PowerGenerationDetail dcAmps(Float dcAmps) {
    //     this.dcAmps = dcAmps;
    //     return this;
    // }

    // public void setDcAmps(Float dcAmps) {
    //     this.dcAmps = dcAmps;
    // }

    // public Float getAcVolts() {
    //     return acVolts;
    // }

    public void setAcVolts1(Float acVolts1) {
        this.acVolts1 = acVolts1;
    }

    public PowerGenerationDetail acVolts1(Float acVolts1) {
        this.acVolts1 = acVolts1;
        return this;
    }

    public Float getAcAmps1() {
        return acAmps1;
    }

    public PowerGenerationDetail acAmps1(Float acAmps1) {
        this.acAmps1 = acAmps1;
        return this;
    }

    public void setAcAmps1(Float acAmps1) {
        this.acAmps1 = acAmps1;
    }

    public void setacVolts2(Float acVolts2) {
        this.acVolts2 = acVolts2;
    }

    public PowerGenerationDetail acVolts2(Float acVolts2) {
        this.acVolts2 = acVolts2;
        return this;
    }

    public Float getAcAmps2() {
        return acAmps2;
    }

    public PowerGenerationDetail acAmps2(Float acAmps2) {
        this.acAmps2 = acAmps2;
        return this;
    }

    public void setAcAmps2(Float acAmps2) {
        this.acAmps2 = acAmps2;
    }

    public void setacVolts3(Float acVolts3) {
        this.acVolts3 = acVolts3;
    }

    public PowerGenerationDetail acVolts3(Float acVolts3) {
        this.acVolts3 = acVolts3;
        return this;
    }

    public Float getAcAmps3() {
        return acAmps3;
    }

    public PowerGenerationDetail acAmps3(Float acAmps3) {
        this.acAmps3 = acAmps3;
        return this;
    }

    public void setAcAmps3(Float acAmps3) {
        this.acAmps3 = acAmps3;
    }

    public Integer getAcPowerWatts() {
        return acPowerWatts;
    }

    public PowerGenerationDetail acPowerWatts(Integer acPowerWatts) {
        this.acPowerWatts = acPowerWatts;
        return this;
    }

    public void setAcPowerWatts(Integer acPowerWatts) {
        this.acPowerWatts = acPowerWatts;
    }

    public Float getAcFrequencyHerz() {
        return acFrequencyHerz;
    }

    public PowerGenerationDetail acFrequencyHerz(Float acFrequencyHerz) {
        this.acFrequencyHerz = acFrequencyHerz;
        return this;
    }

    public void setAcFrequencyHerz(Float acFrequencyHerz) {
        this.acFrequencyHerz = acFrequencyHerz;
    }

    public Float getAcPowerPercent() {
        return acPowerPercent;
    }

    public PowerGenerationDetail acPowerPercent(Float acPowerPercent) {
        this.acPowerPercent = acPowerPercent;
        return this;
    }

    public void setAcPowerPercent(Float acPowerPercent) {
        this.acPowerPercent = acPowerPercent;
    }

    public Float getTemperatureHeatSink() {
        return temperatureHeatSink;
    }

    public PowerGenerationDetail temperatureHeatSink(Float temperatureHeatSink) {
        this.temperatureHeatSink = temperatureHeatSink;
        return this;
    }

    public void setTemperatureHeatSink(Float temperatureHeatSink) {
        this.temperatureHeatSink = temperatureHeatSink;
    }

    public Integer getOperationState() {
        return operationState;
    }

    public PowerGenerationDetail operationState(Integer operationState) {
        this.operationState = operationState;
        return this;
    }

    public void setOperationState(Integer operationState) {
        this.operationState = operationState;
    }

    @Override
    public String toString() {
        return "PowerGenerationDetail [" + "dataAtDateTime=" + dataAtDateTime + ", address=" + address
        // + ", dcAmps=" + dcAmps + ", dcVolts=" + dcVolts
                + ", acVolts1=" + acVolts1 + ", acVolts2=" + acVolts2 + ", acVolts3=" + acVolts3 + ", acAmps1="
                + acAmps1 + ", acAmps2=" + acAmps2 + ", acAmps3=" + acAmps3 + ", acFrequencyHerz=" + acFrequencyHerz
                + ", acPowerPercent=" + acPowerPercent + ", acPowerWatts=" + acPowerWatts + ", operationState="
                + operationState + ", temperatureHeatSink=" + temperatureHeatSink + ", wattHoursToday=" + wattHoursToday
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((acAmps1 == null) ? 0 : acAmps1.hashCode());
        result = prime * result + ((acAmps2 == null) ? 0 : acAmps2.hashCode());
        result = prime * result + ((acAmps3 == null) ? 0 : acAmps3.hashCode());
        result = prime * result + ((acFrequencyHerz == null) ? 0 : acFrequencyHerz.hashCode());
        result = prime * result + ((acPowerPercent == null) ? 0 : acPowerPercent.hashCode());
        result = prime * result + ((acPowerWatts == null) ? 0 : acPowerWatts.hashCode());
        result = prime * result + ((acVolts1 == null) ? 0 : acVolts1.hashCode());
        result = prime * result + ((acVolts2 == null) ? 0 : acVolts2.hashCode());
        result = prime * result + ((acVolts3 == null) ? 0 : acVolts3.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((dataAtDateTime == null) ? 0 : dataAtDateTime.hashCode());
        // result = prime * result + ((dcAmps == null) ? 0 : dcAmps.hashCode());
        // result = prime * result + ((dcVolts == null) ? 0 : dcVolts.hashCode());
        result = prime * result + ((operationState == null) ? 0 : operationState.hashCode());
        result = prime * result + ((temperatureHeatSink == null) ? 0 : temperatureHeatSink.hashCode());
        result = prime * result + ((wattHoursToday == null) ? 0 : wattHoursToday.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PowerGenerationDetail other = (PowerGenerationDetail) obj;
        if (acAmps1 == null) {
            if (other.acAmps1 != null)
                return false;
        } else if (!acAmps1.equals(other.acAmps1))
            return false;
        if (acAmps2 == null) {
            if (other.acAmps2 != null)
                return false;
        } else if (!acAmps2.equals(other.acAmps2))
            return false;
        if (acAmps3 == null) {
            if (other.acAmps3 != null)
                return false;
        } else if (!acAmps3.equals(other.acAmps3))
            return false;
        if (acFrequencyHerz == null) {
            if (other.acFrequencyHerz != null)
                return false;
        } else if (!acFrequencyHerz.equals(other.acFrequencyHerz))
            return false;
        if (acPowerPercent == null) {
            if (other.acPowerPercent != null)
                return false;
        } else if (!acPowerPercent.equals(other.acPowerPercent))
            return false;
        if (acPowerWatts == null) {
            if (other.acPowerWatts != null)
                return false;
        } else if (!acPowerWatts.equals(other.acPowerWatts))
            return false;
        if (acVolts1 == null) {
            if (other.acVolts1 != null)
                return false;
        } else if (!acVolts1.equals(other.acVolts1))
            return false;
        if (acVolts2 == null) {
            if (other.acVolts2 != null)
                return false;
        } else if (!acVolts2.equals(other.acVolts2))
            return false;
        if (acVolts3 == null) {
            if (other.acVolts3 != null)
                return false;
        } else if (!acVolts3.equals(other.acVolts3))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (dataAtDateTime == null) {
            if (other.dataAtDateTime != null)
                return false;
        } else if (!dataAtDateTime.equals(other.dataAtDateTime))
            return false;
        // if (dcAmps == null) {
        //     if (other.dcAmps != null)
        //         return false;
        // } else if (!dcAmps.equals(other.dcAmps))
        //     return false;
        // if (dcVolts == null) {
        //     if (other.dcVolts != null)
        //         return false;
        // } else if (!dcVolts.equals(other.dcVolts))
        //     return false;
        if (operationState == null) {
            if (other.operationState != null)
                return false;
        } else if (!operationState.equals(other.operationState))
            return false;
        if (temperatureHeatSink == null) {
            if (other.temperatureHeatSink != null)
                return false;
        } else if (!temperatureHeatSink.equals(other.temperatureHeatSink))
            return false;
        if (wattHoursToday == null) {
            if (other.wattHoursToday != null)
                return false;
        } else if (!wattHoursToday.equals(other.wattHoursToday))
            return false;
        return true;
    }

}
