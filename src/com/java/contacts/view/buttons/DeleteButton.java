package com.java.contacts.view.buttons;

import com.java.contacts.controller.ContactsController;
import com.java.contacts.view.listeners.DeleteButtonActionListener;

import javax.swing.*;

public class DeleteButton extends JButton {

    private ContactsController contactsController;

    public DeleteButton(ContactsController controller) {
        super("Delete");
        contactsController = controller;
        addActionListener(new DeleteButtonActionListener(contactsController));
    }

}
