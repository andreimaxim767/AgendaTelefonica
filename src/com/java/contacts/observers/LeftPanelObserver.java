package com.java.contacts.observers;


import com.java.contacts.model.Contact;

public interface LeftPanelObserver {

    void selectedContactDidChanged(Contact aContact);
}
