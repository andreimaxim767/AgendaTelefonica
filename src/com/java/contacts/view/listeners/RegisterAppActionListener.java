package com.java.contacts.view.listeners;

import com.java.contacts.view.MenuBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterAppActionListener implements ActionListener {

    private MenuBar menuBar;

    public RegisterAppActionListener(MenuBar jMenuBar) {
        menuBar = jMenuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String serialNumber = JOptionPane.showInputDialog("Registration code");
        if (serialNumber != null) {
            if (serialNumber.equals("agenda")) {
                JOptionPane.showMessageDialog(null, "Aplicatia a fost inregistrata!");
                menuBar.activateAppMenu();
            } else {
                JOptionPane.showMessageDialog(null, "Cod de inregistrare incorect!");
            }
        }

    }
}
