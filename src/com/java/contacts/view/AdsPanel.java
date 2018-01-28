
package com.java.contacts.view;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Afisarea reclamelor in partea de jos a aplicatiei.
 * Obiectul creat este de tip JPanel si foloseste 3 imagini pe care le roteste
 * aleator  la fiecare 2 secunde.
 *
 * @author Maxim Andrei
 */
public class AdsPanel extends JPanel {

    private JLabel imageLabel;
    private ImageIcon adImage1;
    private ImageIcon adImage2;
    private ImageIcon adImage3;
    private int randomNumber;
    private static final int BANNER_DISPLAY_TIME = 2000; //milisecunde
    private Random rand = new Random();

    private Thread mainAdsThread = null;

    /**
     * Initializare thread si rotirea imaginilor la 2 secunde
     */
    public AdsPanel() {
        setSize(390, 100);
        adImage1 = new ImageIcon("resources/images/1.jpg");
        adImage2 = new ImageIcon("resources/images/2.jpg");
        adImage3 = new ImageIcon("resources/images/3.jpg");
        imageLabel = new JLabel(adImage1);
        imageLabel.setBounds(0, 0, 390, 90);
        add(imageLabel);

        mainAdsThread = new Thread() {

            @Override
            public void run() {
                while (true) {
                    randomNumber = rand.nextInt(4);

                    if (randomNumber == 3) {
                        imageLabel.setIcon(adImage3);
                        repaint();
                        try {
                            sleep(BANNER_DISPLAY_TIME);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (randomNumber == 2) {
                        imageLabel.setIcon(adImage2);
                        repaint();
                        try {
                            sleep(BANNER_DISPLAY_TIME);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (randomNumber == 1) {
                        imageLabel.setIcon(adImage1);
                        repaint();
                        try {
                            sleep(BANNER_DISPLAY_TIME);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            }

        };
        mainAdsThread.start();

    }

}
