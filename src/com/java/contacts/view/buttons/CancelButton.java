package com.java.contacts.view.buttons;

import com.java.contacts.controller.ContactsController;
import com.java.contacts.view.listeners.CancelButtonActionListener;

import javax.swing.*;

public class CancelButton extends JButton {

    ContactsController contactsController;

    public CancelButton(ContactsController controller) {
        super("Cancel");
        contactsController = controller;
        addActionListener(new CancelButtonActionListener(contactsController));
    }

}
