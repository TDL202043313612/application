package com.example.applications.entity;

import java.util.List;

public class HF3DWeatherResponse {

    private BasicDTO basic;
    private String code;
    private ReferDTO refer;
    private List<HF3DWeatherEntity> daily;

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

    public ReferDTO getRefer() {
        return refer;
    }

    public void setRefer(ReferDTO refer) {
        this.refer = refer;
    }

    public List<HF3DWeatherEntity> getDaily() {
        return daily;
    }

    public void setDaily(List<HF3DWeatherEntity> daily) {
        this.daily = daily;
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
