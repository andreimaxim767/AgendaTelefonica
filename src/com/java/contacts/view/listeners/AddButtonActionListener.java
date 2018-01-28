package com.java.contacts.view.listeners;

import com.java.contacts.controller.ContactsController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddButtonActionListener implements ActionListener {

    private ContactsController contactsController;

    public AddButtonActionListener(ContactsController contactsController) {
        this.contactsController = contactsController;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        contactsController.addContact();
    }
}
