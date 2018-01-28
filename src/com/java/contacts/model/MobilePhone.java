package com.java.contacts.model;

public class MobilePhone extends Phone {

    private String mobil;

    public MobilePhone(String mobil) {
        super(mobil);
        this.mobil = mobil;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    @Override
    public String toString() {
        return mobil;
    }

}
