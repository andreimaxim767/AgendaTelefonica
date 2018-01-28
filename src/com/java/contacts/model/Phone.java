package com.java.contacts.model;

public abstract class Phone {

    private String numar;

    public Phone(String numar) {
        this.numar = numar;
    }

    public String getNumar() {
        return numar;
    }

    public void setNumar(String numar) {
        this.numar = numar;
    }

    @Override
    public String toString() {
        return numar;
    }


}