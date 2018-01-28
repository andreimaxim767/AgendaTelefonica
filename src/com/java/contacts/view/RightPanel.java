package com.java.contacts.view;

import com.java.contacts.controller.ContactsController;
import com.java.contacts.model.ContactsModel;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {

    private DetailsPanel detailsPanel;
    private ButtonsPanel buttonsPanel;
    private AdsPanel adsPanel;

    public RightPanel(ContactsController contactsController, ContactsModel contactsModel) {
        detailsPanel = new DetailsPanel(contactsModel);
        buttonsPanel = new ButtonsPanel(contactsController);
        adsPanel = new AdsPanel();

        init();
    }

    private void init() {
        setSize(new Dimension(400,600));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(detailsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(buttonsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(adsPanel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.GRAY);
    }

    public DetailsPanel getDetailsPanel() {
        return detailsPanel;
    }
}
