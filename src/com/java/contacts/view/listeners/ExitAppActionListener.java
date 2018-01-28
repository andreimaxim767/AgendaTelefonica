package com.java.contacts.view.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitAppActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int exitDialogResult = JOptionPane.showConfirmDialog(null, "Confirm exiting the app?", "Exit application", dialogButton);
        if (exitDialogResult == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
