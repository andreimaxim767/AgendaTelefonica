package com.java.contacts.controller;

import com.java.contacts.model.Contact;
import com.java.contacts.model.ContactsModel;
import com.java.contacts.view.ContactsView;

import java.util.Optional;

public class ContactsController {

    private ContactsView contactsView;
    private ContactsModel contactsModel;



    public ContactsController(ContactsModel model) {
        contactsModel = model;
        contactsView = new ContactsView(this, contactsModel);
        registerContactObservers();
    }

    public ContactsController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void init() {
        contactsView.init();
    }


    public void deleteContact() {
        contactsView.deleteContactFromTable();

    }

    public void addContact() {
        contactsView.addContactFromView();
    }

    public void clearInputs() {
        contactsView.clearContactDetailsInput();
    }

    public void update() {
        System.out.println("Updated");
        Contact contactFromDetailsPanel = contactsView.getContactFromDetailsPanel();
        Optional<Contact> selectedContactFromTable = contactsView.getSelectedContactFromTable();
        if(selectedContactFromTable.isPresent()) {
            contactsModel.updateContact(selectedContactFromTable.get(), contactFromDetailsPanel);
            clearInputs();
        }

    }

    private void registerContactObservers() {
        contactsModel.registerObserver(contactsView);
    }
}
