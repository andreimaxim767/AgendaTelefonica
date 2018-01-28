package com.java.contacts.view;

import com.java.contacts.controller.ContactsController;
import com.java.contacts.model.ContactsModel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    public MainPanel(ContactsController contactsController, ContactsModel contactsModel) {
        leftPanel = new LeftPanel(contactsModel);
        rightPanel = new RightPanel(contactsController, contactsModel);

        registerLeftPanelObservers();
        init();
    }

    private void init() {
        setLayout(new GridLayout(1,2));
        add(leftPanel);
        add(rightPanel);
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }

    public void registerLeftPanelObservers() {
        leftPanel.registerObserver(rightPanel.getDetailsPanel());
    }
}
