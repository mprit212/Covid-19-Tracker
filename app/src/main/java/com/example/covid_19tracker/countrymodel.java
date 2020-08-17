package com.example.covid_19tracker;

public class countrymodel
{

    private String flag,country,cases,todaycases,deaths,todaydeaths,recoverd,active,critical,casepermill,deathpermill,testpermill,totaltest;

   public countrymodel()
    {

    }

    public countrymodel(String flag, String country, String cases, String todaycases, String deaths, String todaydeaths, String recoverd, String active, String critical) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydeaths = todaydeaths;
        this.recoverd = recoverd;
        this.active = active;
        this.critical = critical;
    }

    public countrymodel(String flag, String country, String cases, String todaycases, String deaths, String todaydeaths, String recoverd, String active, String critical, String casepermill, String deathpermill, String testpermill, String totaltest) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydeaths = todaydeaths;
        this.recoverd = recoverd;
        this.active = active;
        this.critical = critical;
        this.casepermill = casepermill;
        this.deathpermill = deathpermill;
        this.testpermill = testpermill;
        this.totaltest = totaltest;
    }

    public String getCasepermill() {
        return casepermill;
    }

    public void setCasepermill(String casepermill) {
        this.casepermill = casepermill;
    }

    public String getDeathpermill() {
        return deathpermill;
    }

    public void setDeathpermill(String deathpermill) {
        this.deathpermill = deathpermill;
    }

    public String getTestpermill() {
        return testpermill;
    }

    public void setTestpermill(String testpermill) {
        this.testpermill = testpermill;
    }

    public String getTotaltest() {
        return totaltest;
    }

    public void setTotaltest(String totaltest) {
        this.totaltest = totaltest;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodaydeaths() {
        return todaydeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todaydeaths = todaydeaths;
    }

    public String getRecoverd() {
        return recoverd;
    }

    public void setRecoverd(String recoverd) {
        this.recoverd = recoverd;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }
}
