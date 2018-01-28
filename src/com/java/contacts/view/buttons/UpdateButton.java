package com.java.contacts.view.buttons;

import com.java.contacts.controller.ContactsController;
import com.java.contacts.view.listeners.UpdateButtonActionListener;

import javax.swing.*;

public class UpdateButton extends JButton {


    private ContactsController contactsController;

    public UpdateButton(ContactsController controller) {
        super("Update");
        contactsController = controller;
        addActionListener(new UpdateButtonActionListener(contactsController));
    }

}
