package com.java.contacts.view;


import com.java.contacts.controller.CarteDeTelefonInitial;
import com.java.contacts.controller.CarteDeTelefonInitial.AbonatPopup;
import com.java.contacts.controller.CarteDeTelefonInitial.AdaugaAbonat;
import com.java.contacts.controller.CarteDeTelefonInitial.CautaAbonat;
import com.java.contacts.controller.CarteDeTelefonInitial.StergeAbonat;

import com.java.contacts.view.listeners.ExitAppActionListener;
import com.java.contacts.view.listeners.RegisterAppActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {

    private final ImageIcon loadIcon = new ImageIcon("resources/icon/open.png");
    private final JMenuItem loadButton = new JMenuItem("Load DB", loadIcon);
    private final ImageIcon saveIcon = new ImageIcon("resources/icon/save.png");
    private final JMenuItem saveButton = new JMenuItem("Save DB", saveIcon);
    private final ImageIcon exitIcon = new ImageIcon("resources/icon/exit.png");
    private final JMenuItem exitButton = new JMenuItem("Exit", exitIcon);
    private final ImageIcon keyIcon = new ImageIcon("resources/icon/key.png");
    private final JMenuItem registerButton = new JMenuItem("Register", keyIcon);
    private final JMenuItem saveContact = new JMenuItem("Save", saveIcon);
    private final JMenu file = new JMenu("File");
    private final JMenu contactsMenu = new JMenu("Contacts");
    private final ImageIcon newIcon = new ImageIcon("resources/icon/new.png");
    private final JMenuItem newContact = new JMenuItem("Add", newIcon);
    private final ImageIcon updateIcon = new ImageIcon("resources/icon/update.png");
    private final JMenuItem updateContact = new JMenuItem("Update", updateIcon);
    private final ImageIcon searchIcon = new ImageIcon("resources/icon/search.png");
    private final JMenuItem searchContact = new JMenuItem("Search", searchIcon);
    private final ImageIcon deleteIcon = new ImageIcon("resources/icon/trash.png");
    private final JMenuItem deleteContact = new JMenuItem("Delete", deleteIcon);
    private final JMenu helpMenu = new JMenu("Help");
    private final ImageIcon helpIcon = new ImageIcon("resources/icon/help.png");
    private final JMenuItem manual = new JMenuItem("Manual", helpIcon);
    private boolean isAppRegistered = false;

    public MenuBar() {
        init();
    }

    public void init() {
        setupFileMenu();
        setupContactsMenu();
        setupHelpMenu();
    }

    private void setupFileMenu() {
        file.setMnemonic(KeyEvent.VK_F);
        setupLoadButton();
        setupSaveButton();
        setupExitButton();
        file.add(loadButton);
        file.add(saveButton);
        file.addSeparator();
        file.add(exitButton);

        this.add(file);
    }

    private void setupContactsMenu() {
        contactsMenu.setMnemonic(KeyEvent.VK_B);
        setupNewContactMenu();
        setupUpdateContactMenu();
        setupSearchContactMenu();
        setupSaveContactMenu();
        setupDeleteContactMenu();
        contactsMenu.add(newContact);
        contactsMenu.add(updateContact);
        contactsMenu.add(saveContact);
        contactsMenu.add(deleteContact);
        contactsMenu.addSeparator();
        contactsMenu.add(searchContact);

        this.add(contactsMenu);
    }

    private void setupHelpMenu() {
        helpMenu.setMnemonic(KeyEvent.VK_H);
        setupManualMenu();
        setupRegisterMenu();
        helpMenu.add(registerButton);
        helpMenu.addSeparator();
        helpMenu.add(manual);

        this.add(helpMenu);
    }

    private void setupLoadButton() {
        loadButton.setMnemonic(KeyEvent.VK_D);
        loadButton.setToolTipText("Load Database");
        loadButton.setEnabled(isAppRegistered);
        loadButton.addActionListener(new CarteDeTelefonInitial().new IncarcaDB());
    }

    private void setupSaveButton() {
        saveButton.setMnemonic(KeyEvent.VK_A);
        saveButton.setToolTipText("Save database");
        saveButton.setEnabled(isAppRegistered);
        saveButton.addActionListener(new CarteDeTelefonInitial().new SalveazaDB());
    }

    private void setupExitButton() {
        exitButton.setMnemonic(KeyEvent.VK_I);
        exitButton.setToolTipText("Exit the app");
        exitButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        exitButton.addActionListener(new ExitAppActionListener());
    }

    private void setupNewContactMenu() {
        newContact.setMnemonic(KeyEvent.VK_A);
        newContact.setToolTipText("Add new contact");
        newContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        //newContact.addActionListener(new CarteDeTelefonInitial().AbonatPopup());
    }

    private void setupUpdateContactMenu() {
        updateContact.setMnemonic(KeyEvent.VK_U);
        updateContact.setToolTipText("Update contact");
        updateContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        updateContact.addActionListener(new CarteDeTelefonInitial().new AbonatPopup());
    }

    private void setupSearchContactMenu() {
        searchContact.setMnemonic(KeyEvent.VK_C);
        searchContact.setToolTipText("Search contact");
        searchContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
       searchContact.addActionListener(new CarteDeTelefonInitial().new CautaAbonat());
    }

    private void setupSaveContactMenu() {
        saveContact.setMnemonic(KeyEvent.VK_S);
        saveContact.setToolTipText("Save contact");
        saveContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveContact.addActionListener(new CarteDeTelefonInitial().new AdaugaAbonat());
    }

    private void setupDeleteContactMenu() {
        deleteContact.setMnemonic(KeyEvent.VK_R);
        deleteContact.setToolTipText("Delete contact");
        deleteContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        deleteContact.addActionListener(new CarteDeTelefonInitial().new StergeAbonat());
    }

    private void setupManualMenu() {
        manual.setMnemonic(KeyEvent.VK_M);
        manual.setToolTipText("Users guide");
        manual.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        
    }

    private void setupRegisterMenu() {
        registerButton.setMnemonic(KeyEvent.VK_R);
        registerButton.setToolTipText("Enter registration code");
        registerButton.addActionListener(new RegisterAppActionListener(this));
    }

    public void activateAppMenu() {
        isAppRegistered = true;
        loadButton.setEnabled(isAppRegistered);
        saveButton.setEnabled(isAppRegistered);
        registerButton.setEnabled(!isAppRegistered);

    }
}