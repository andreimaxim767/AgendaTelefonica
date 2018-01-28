package com.java.contacts.model;

public class LandPhone extends Phone {

    private String fix;

    public LandPhone(String fix) {
        super(fix);
        this.fix = fix;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

    @Override
    public String toString() {
        return fix;
    }

}