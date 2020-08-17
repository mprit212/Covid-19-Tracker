package com.example.covid_19tracker;

public class helpmodel
{

    private String state,totalcase,deaths,recover,active;

    public helpmodel(String state, String totalcase, String deaths, String recover, String active) {
        this.state = state;
        this.totalcase = totalcase;
        this.deaths = deaths;
        this.recover = recover;
        this.active = active;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTotalcase() {
        return totalcase;
    }

    public void setTotalcase(String totalcase) {
        this.totalcase = totalcase;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecover() {
        return recover;
    }

    public void setRecover(String recover) {
        this.recover = recover;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
