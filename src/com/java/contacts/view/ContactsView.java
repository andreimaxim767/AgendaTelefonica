package com.java.contacts.view;

import com.java.contacts.observers.ContactObserver;
import com.java.contacts.controller.ContactsController;
import com.java.contacts.model.Contact;
import com.java.contacts.model.ContactsModel;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class ContactsView implements ContactObserver{

    private JFrame viewFrame;
    private MenuBar menuBar;
    private MainPanel mainPanel;

    private ContactsController contactsController;
    private ContactsModel contactsModel;

    public ContactsView(ContactsController contactsController, ContactsModel contactsModel) {
        this.contactsController = contactsController;
        this.contactsModel = contactsModel;
    }

    public ContactsView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void init() {
        viewFrame = new JFrame("Contacts");
        mainPanel = new MainPanel(contactsController, contactsModel);
        menuBar = new MenuBar();

        viewFrame.setLayout(new GridLayout(1,1));
        viewFrame.getRootPane().setJMenuBar(menuBar);

        viewFrame.setSize(800, 600);

        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.getContentPane().add(mainPanel);
        viewFrame.setVisible(true);

    }

    public void clearContactDetailsInput() {
        mainPanel.getRightPanel().getDetailsPanel().clearInputs();
    }

    public void addContactFromView() {
        DetailsPanel detailsPanel = mainPanel.getRightPanel().getDetailsPanel();
        Contact contactToInsert = detailsPanel.getContactToInsert();
        mainPanel.getLeftPanel().addContactToModel(contactToInsert);
    }

    public void deleteContactFromTable() {
        mainPanel.getLeftPanel().deleteContactFromModel();
    }

    @Override
    public void updateViewOnContactAdded() {
        System.out.println("contact added notification");
        mainPanel.getRightPanel().getDetailsPanel().clearInputs();
    }

    @Override
    public void updateViewOnContactRemoved() {
        System.out.println("contact removed notification");
        mainPanel.getRightPanel().getDetailsPanel().clearInputs();
    }

    public Contact getContactFromDetailsPanel() {
        return mainPanel.getRightPanel().getDetailsPanel().getContactToInsert();
    }

    public Optional<Contact> getSelectedContactFromTable() {
        return mainPanel.getLeftPanel().getSelectedContactFromTable();
    }
}
