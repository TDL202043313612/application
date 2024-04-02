package com.example.applications.entity;

import java.util.List;

public class HFCityResponse {

    private String code;
    private ReferDTO refer;
    private List<HFCityEntity> locationBean;

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

    public List<HFCityEntity> getLocationBean() {
        return locationBean;
    }

    public void setLocationBean(List<HFCityEntity> locationBean) {
        this.locationBean = locationBean;
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
