package com.java.contacts.view.buttons;

import com.java.contacts.controller.ContactsController;
import com.java.contacts.view.listeners.AddButtonActionListener;

import javax.swing.*;

public class AddButton extends JButton {

    ContactsController contactsController;

    public AddButton(ContactsController controller) {
        super("Add");
        contactsController = controller;
        addActionListener(new AddButtonActionListener(contactsController));
    }

}
