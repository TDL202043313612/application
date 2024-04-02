package com.example.applications.entity;

import java.util.List;

public class HFNowWeatherResponse {

    private BasicDTO basic;
    private String code;
    private HFNowWeatherEntity now;
    private ReferDTO refer;

    public BasicDTO getBasic() {
        return basic;
    }

    public void setBasic(BasicDTO basic) {
        this.basic = basic;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HFNowWeatherEntity getNow() {
        return now;
    }

    public void setNow(HFNowWeatherEntity now) {
        this.now = now;
    }

    public ReferDTO getRefer() {
        return refer;
    }

    public void setRefer(ReferDTO refer) {
        this.refer = refer;
    }

    public static class BasicDTO {
        private String fxLink;
        private String updateTime;

        public String getFxLink() {
            return fxLink;
        }

        public void setFxLink(String fxLink) {
            this.fxLink = fxLink;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }



    public static class ReferDTO {
        private List<String> licenseList;
        private List<String> sourcesList;

        public List<String> getLicenseList() {
            return licenseList;
        }

        public void setLicenseList(List<String> licenseList) {
            this.licenseList = licenseList;
        }

        public List<String> getSourcesList() {
            return sourcesList;
        }

        public void setSourcesList(List<String> sourcesList) {
            this.sourcesList = sourcesList;
        }
    }
}
