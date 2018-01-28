package com.java.contacts.view.listeners;

import com.java.contacts.controller.ContactsController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonActionListener implements ActionListener {

    private ContactsController contactsController;

    public DeleteButtonActionListener(ContactsController controller) {
        contactsController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        contactsController.deleteContact();
    }
}
