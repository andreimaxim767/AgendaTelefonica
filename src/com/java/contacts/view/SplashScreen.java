package com.java.contacts.view;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SplashScreen afisat la pornirea aplicatiei
 *
 * @author Maxim Andrei
 */
public class SplashScreen extends JFrame {

    private final JLabel labelImagine;
    private final ImageIcon imagineFundal;
    private static JProgressBar baraProgres;
    private Thread threadPrincipal = null;

    public SplashScreen() {
        super("Initializare Agenda Telefonica");
        setSize(340, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(null);

        imagineFundal = new ImageIcon("resources/images/prog.gif");
        labelImagine = new JLabel(imagineFundal);
        labelImagine.setBounds(20, 20, 300, 187);
        add(labelImagine);

        JLabel labelAutor = new JLabel("Autor Maxim Andrei");
        add(labelAutor);
        labelAutor.setBounds(215, 210, 150, 20);

        baraProgres = new JProgressBar();
        baraProgres.setMinimum(0);
        baraProgres.setMaximum(100);
        baraProgres.setStringPainted(true);
        baraProgres.setBackground(Color.RED);

        baraProgres.setBounds(20, 240, 300, 40);
        add(baraProgres);

        threadPrincipal = new Thread() {

            @Override
            public void run() {
                int i = 0;
                while (i <= 100) {
                    baraProgres.setValue(i);
                    try {
                        sleep(35);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SplashScreen.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                    i++;
                }
            }
        };

    }

    /**
     * Metoda de start prin care se porneste thread-ul de Splash Screen
     */
    public void start() {
        threadPrincipal.start();
    }


}