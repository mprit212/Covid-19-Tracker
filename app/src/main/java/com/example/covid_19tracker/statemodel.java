package com.example.covid_19tracker;

public class statemodel {

    private String  state,district,active,confirmed,deceased,recoverd;

    public statemodel()
    {

    }

    public statemodel(String state) {
        this.state = state;
    }

    public statemodel(String district, String active, String confirmed, String deceased, String recoverd) {

        this.district = district;
        this.active = active;
        this.confirmed = confirmed;
        this.deceased = deceased;
        this.recoverd = recoverd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeceased() {
        return deceased;
    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;
    }

    public String getRecoverd() {
        return recoverd;
    }

    public void setRecoverd(String recoverd) {
        this.recoverd = recoverd;
    }
}
