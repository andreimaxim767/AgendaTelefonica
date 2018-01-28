package com.java.contacts.controller;

import com.java.contacts.database.MySQL;
import com.java.contacts.model.Contact;
import com.java.contacts.model.Phone;
import com.java.contacts.model.PhoneNumberType;
import com.java.contacts.view.AdsPanel;
import com.java.contacts.view.MenuBar;
import com.java.contacts.view.ButtonsPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;

public class CarteDeTelefonInitial extends JFrame {


    private static final long serialVersionUID = 1L;

    private final transient PhoneNumberType nrTel = new PhoneNumberType();
    private final transient Connection conn = MySQL.getConnection();
    private transient Statement stmt;

    private DefaultTableModel model;
    private final JTable tabelPopulat = new JTable();
    private static int randSelectat = 0;

    private final JTextField numeText = new JTextField(10);
    private final JTextField prenumeText = new JTextField(10);
    private final JTextField cnpText = new JTextField(10);
    private final JTextField telefonText = new JTextField(10);
    private final JTextField cautareText = new JTextField(30);

    /**
     * Clasa ActivareInput pentru activarea aplicatiei
     * Este folosita in momentul in care s-a introdus codul de inregistrare
     * Efectul este ca activeaza Incarcarea/Salvarea DB, si dezactiveaza meniul
     * de Inregistrare. De asemenea, activeaza campurile de introducere
     * a datelor.
     */
    public class ActivareAplicatie implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            activeazaAplicatie();
        }
    }

    /**
     * Clasa AbonatPopup pentru afisarea ferestrei de
     * adaugare/modificare a datelor abonatilor.
     **/
    public class AbonatPopup implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            popupAbonat();
        }

    }

    
    public class AdaugaAbonat implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            adaugareAbonat();
            stergereInput();
            actualizareTabel();
        }
    }

    /**
     * Clasa IncarcaDB pentru incarcarea bazei de date.
     * Incarcarea se face din fisierul aflat in folderul proiectului.
      Foloseste metoda incarcareInregistrari().
     */
    public class IncarcaDB implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("IncarcareDB");
            incarcareInregistrari();
        }

    }

    /**
     * Clasa SalveazaDB pentru salvarea bazei de date.
     * Salvarea se face intr-un fisier aflat la folderul proiectului.
     * Foloseste metoda salvareInregistrari().
     */
    public class SalveazaDB implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("SalvareDB");
            salvareInregistrari();
        }

    }

    /**
     * Clasa StergeAbonat pentru stergerea abonatilor din baza.
     * Foloseste metoda stergeAbonat().
     */
    public class StergeAbonat implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            stergeAbonat();
        }
    }

    /**
     * Clasa ActualizeazaAbonat pentru actualizarea abonatilor.
     * Foloseste metodele:
     * actualizeazaAbonat() 
     * stergereInput() 
     */
    public class ActualizeazaAbonat implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            actualizeazaAbonat();
            stergereInput();
        }
    }

    
    public class AnuleazaInput implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            stergereInput();
            aplicatieShareware();
        }
    }

   
    public class CautaAbonat implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            cautareAbonat();
        }
    }

    /**
     * Clasa SelectieTabel pentru selectarea randului din tabel
     * care implementeaza ListSelectionListener pentru completarea datelor
     * in campurile de input dupa selectarea unui rand
     * Foloseste metoda stergeAbonat().
     */
    public class SelectieTabel implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                selecteazaRand();
            }
        }
    }

    /**
     * Clasa EditareAbonatClick pentru a putea edita un abonat
     * in momentul in care s-a dat dublu click pe randul din tabel cu inregistrarea
     * pe care se vrea modificarea.
     * Foloseste metoda stergeAbonat().
     */
    public class EditareAbonatClick implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if (me.getClickCount() == 2) {
                popupAbonat();
            }

        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }

    }

    /**
     * Clasa StergereRandAbonat pentru stergerea abonatului la
     * apasarea tastei DELETE. Dupa apasarea tastei, utilizatorul este
     * intrebat daca doreste stergerea.
     * Foloseste metoda stergeAbonat() 
     */
    public class StergereRandAbonat implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            int c = e.getKeyCode();
            if (c == KeyEvent.VK_DELETE) {
                int valoareMesaj = JOptionPane.showConfirmDialog(tabelPopulat, "Doriti stergerea abonatului?", "Confirmati stergerea", JOptionPane.YES_NO_OPTION);
                int[] index = tabelPopulat.getSelectedRows();
                e.consume();
                stergeAbonat();
                if (valoareMesaj == JOptionPane.YES_OPTION) {
                    for (int i = index.length - 1; i >= 0; --i) {
                        model.removeRow(index[i]);
                    }
                } else if (valoareMesaj == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Abonatul nu a fost sters!");
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println("No implementation of KeyTyped");
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("No implementation of KeyTyped");
        }

    }

    

    /**
     * Metoda pentru obtinere caii pentru deschiderea/citirea din fisier
     *
     * @return path Calea catre fisier
     */
    public String getFisierBazaDate() {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selectati locatia fisierului ");
        int returnVal = chooser.showSaveDialog(this);
        String path = "";
        String file = "";
        String separator = System.getProperty("file.separator");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile().getName();
            path = chooser.getCurrentDirectory().toString();
        }
        return path + separator + file;
    }

    /**
     * Metoda pentru afisarea ferestrei de adaugare/editare abonat
     */
    public void popupAbonat() {
        JFrame popupAbonat = new JFrame("Adauga abonat nou");
        JPanel panouAbonat = new JPanel(new GridBagLayout());
        popupAbonat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panouAbonat.add(panouAdaugare());
        panouAbonat.add(panouButoaneFereastra());
        popupAbonat.getContentPane().add(panouAbonat);
        popupAbonat.pack();
        popupAbonat.setVisible(true);
    }

    /**
     * Metoda principala de afisare a tuturor componentelor
     * Tipul de layout folosit: GridBagLayout
     * Contine panourile pentru tabel, butoane si casutele de adaugare abonat
     */
    public void afisare() {
        /*
         * La apelarea metodei se verifica daca aplicatia este inregistrata.
         * Pe langa aceasta verificare, se face si actualizarea dateleor
         * din tabel.
         */
        aplicatieShareware();
        actualizareTabel();


        /*
         * Declararea File Menu foloseste atat iconite pentru fiecare element
         * din meniu, dar si mnemonice pentru elementele din meniu.
         */

        MenuBar topMenu = new MenuBar();
        setJMenuBar(topMenu);


        /*
         * Panoul principal care foloseste ca layout manager GridBagLayout
         * Este construit adaugand in interiorul lui celelalte JPanel-uri
         * prin apelarea metodelor de construire a acestora
         */
        JPanel panouPrincipal = new JPanel(new GridBagLayout());
        this.getContentPane().add(panouPrincipal);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panouPrincipal.add(panouCautare(), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panouPrincipal.add(new JScrollPane(tabelPopulat), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panouPrincipal.add(new ButtonsPanel(null), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panouPrincipal.add(new AdsPanel(), gbc);

        this.pack();
        this.setVisible(true);
    }

    /**
     * Panoul pentru adaugare/modificare abonati
     */
    private JPanel panouAdaugare() {
        JPanel panouInterfataAdaugare = new JPanel();
        panouInterfataAdaugare.setMinimumSize(new Dimension(200, 200));

        JLabel numeLabel = new JLabel("Nume");
        JLabel prenumeLabel = new JLabel("Prenume");
        JLabel cnpLabel = new JLabel("CNP");
        JLabel telefonLabel = new JLabel("Telefon");

        panouInterfataAdaugare.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.NORTHEAST;

        cautareText.setMinimumSize(cautareText.getPreferredSize());
        numeText.setMinimumSize(cautareText.getPreferredSize());
        prenumeText.setMinimumSize(cautareText.getPreferredSize());
        cnpText.setMinimumSize(cautareText.getPreferredSize());
        telefonText.setMinimumSize(cautareText.getPreferredSize());

        int i = 0;

        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panouInterfataAdaugare.add(numeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panouInterfataAdaugare.add(numeText, gbc);

        i++;

        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panouInterfataAdaugare.add(prenumeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panouInterfataAdaugare.add(prenumeText, gbc);

        i++;

        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panouInterfataAdaugare.add(cnpLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panouInterfataAdaugare.add(cnpText, gbc);

        i++;

        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panouInterfataAdaugare.add(telefonLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panouInterfataAdaugare.add(telefonText, gbc);

        return panouInterfataAdaugare;
    }

    /**
     * Panoul pentru cautarea abonatilor
     */
    private JPanel panouCautare() {
        JPanel panouInterfataCautare = new JPanel();
        JLabel cautareLabel = new JLabel("Filtrare abonati");
        JButton cautaAbonat = new JButton("Cauta");
        cautaAbonat.addActionListener(new CautaAbonat());

        panouInterfataCautare.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        cautareText.setMinimumSize(cautareText.getPreferredSize());

        int i = 0;

        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.anchor = GridBagConstraints.WEST;
        panouInterfataCautare.add(cautareLabel, gbc);

        i++;

        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.ipadx = 8;
        gbc.ipady = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panouInterfataCautare.add(cautareText, gbc);

        gbc.gridx = 1;
        gbc.gridy = i;
        gbc.ipady = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panouInterfataCautare.add(cautaAbonat, gbc);

        return panouInterfataCautare;
    }

    /**
     * Panoul de afisare a butoanelor din fereastra de adaugare/editare
     */
    private JPanel panouButoaneFereastra() {
        JPanel panouButoaneFereastra = new JPanel(new GridBagLayout());

        JButton butonFereastraAdauga = new JButton("Adauga");
        butonFereastraAdauga.addActionListener(new AdaugaAbonat());

        JButton butonFereastraSterge = new JButton("Sterge");
        butonFereastraSterge.addActionListener(new StergeAbonat());

        JButton butonFereastraActualizeaza = new JButton("Modifica");
        butonFereastraActualizeaza.addActionListener(new ActualizeazaAbonat());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 1);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 5;
        panouButoaneFereastra.add(butonFereastraAdauga, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 10;
        panouButoaneFereastra.add(butonFereastraSterge, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 0;
        panouButoaneFereastra.add(butonFereastraActualizeaza, gbc);

        return panouButoaneFereastra;
    }


    /**
     * Metoda de activare a input-urilor de introducere a datelor, si a
     * meniurilor de incarcare,salvare si dezactivare a meniului de inregistrare
     */
    private void activeazaAplicatie() {
        numeText.setEnabled(true);
        prenumeText.setEnabled(true);
        cnpText.setEnabled(true);
        telefonText.setEnabled(true);


    }


    /**
     * Metoda de dezactivare a input-urilor de introducere a datelor si a
     * meniurilor de incarcare si salvare baza de date
     */
    private void aplicatieShareware() {
        numeText.setEnabled(false);
        prenumeText.setEnabled(false);
        cnpText.setEnabled(false);
        telefonText.setEnabled(false);


    }

    /**
     * Metoda de stergere a input-urilor de introducere a datelor
     */
    private void stergereInput() {
        cautareText.setText(null);
        numeText.setText(null);
        prenumeText.setText(null);
        cnpText.setText(null);
        telefonText.setText(null);
    }

    /**
     * Incarcarea inregistrarilor bazei de date din fisierul care le contine.
     * Se citeste din fisier linie cu linie, si se foloseste ca delimitator
     * caracterul virgula.
     * Citirea din fisier se face fara verificari suplimentare pentru ca
     * salvarea datelor se face din baza de date intr-un format predefinit.
     */
    private void incarcareInregistrari() {
        PreparedStatement ps = null;
        BufferedReader reader;
        String linie;
        String nume;
        String prenume;
        String cnp;
        Phone numar;
        int n = 0;

        try {
            String fisierBazaDate = getFisierBazaDate();
            reader = new BufferedReader(new FileReader(fisierBazaDate));

            while ((linie = reader.readLine()) != null) {
                nume = linie.split(",")[1];
                prenume = linie.split(",")[2];
                cnp = linie.split(",")[3];
                numar = nrTel.getPhoneNumberType(linie.split(",")[4]);

                String adaugaQuery = "INSERT INTO ABONAT(nume,prenume,cnp,telefon)"
                        + "VALUES(?,?,?,?)";
                ps = conn.prepareStatement(adaugaQuery);
                ps.setString(1, nume);
                ps.setString(2, prenume);
                ps.setString(3, cnp);
                ps.setString(4, numar.toString());

                n = ps.executeUpdate();
            }
            if (n > 0) {
                actualizareTabel();
                stergereInput();
                JOptionPane.showMessageDialog(null, "Date incarcate cu succes!");
            }
            reader.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Eroare: " + ex.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ContactsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Salvarea inregistrarilor bazei de date in fisierul
     * inregistrari.db
     * Salvarea se face din baza de date, intr-un format usor de citit la un
     * eventual import al datelor, care sa nu presupuna validari suplimentare.
     */
    public void salvareInregistrari() {
        ResultSet rs = null;
        BufferedWriter writer;
        try {
            String fisierBazaDate = getFisierBazaDate();
            writer = new BufferedWriter(new FileWriter(fisierBazaDate));

            String sql = "select * from abonat";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            String linie;

            while (rs.next()) {
                linie = rs.getString("id") + "," + rs.getString("nume") + ","
                        + rs.getString("prenume") + "," + rs.getString("cnp")
                        + "," + rs.getString("telefon") + "\n";
                writer.write(linie);
            }
            writer.close();
        } catch (SQLException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Eroare: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ContactsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    /**
     * Metoda pentru actualizarea informatiilor din tabel la schimbarea
     * datelor (adaugare/modificare/stergere)
     */
    public void actualizareTabel() {
        ResultSet rs = null;
        try {
            String[] coloane = {"Nr.#", "Nume", "Prenume", "CNP", "Telefon"};
            String sql = "select * from abonat";
            model = new DefaultTableModel(null, coloane);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            /*
             * Folosirea TableRowSorter pentru a ordona datele din tabel prin
             * selectarea unui cap de coloana
             */
            final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
            tabelPopulat.setRowSorter(sorter);

            /*
             * Adaugare listener pentru input-ul de cautare pentru filtrarea
             * datelor din tabel
             */
            cautareText.getDocument().addDocumentListener(new DocumentListener() {
                private void searchFieldChangedUpdate(DocumentEvent evt) {
                    String text = cautareText.getText();
                    if (text.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        try {
                            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        } catch (PatternSyntaxException pse) {
                            JOptionPane.showMessageDialog(null, "Bad regex pattern",
                                    "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                @Override
                public void insertUpdate(DocumentEvent evt) {
                    searchFieldChangedUpdate(evt);
                }

                @Override
                public void removeUpdate(DocumentEvent evt) {
                    searchFieldChangedUpdate(evt);
                }

                @Override
                public void changedUpdate(DocumentEvent evt) {
                    searchFieldChangedUpdate(evt);
                }
            });

            String[] linie = new String[5];

            while (rs.next()) {
                linie[0] = rs.getString("id");
                linie[1] = rs.getString("nume");
                linie[2] = rs.getString("prenume");
                linie[3] = rs.getString("cnp");
                linie[4] = rs.getString("telefon");
                model.addRow(linie);
            }
            tabelPopulat.setModel(model);
            tabelPopulat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tabelPopulat.getSelectionModel().addListSelectionListener(new SelectieTabel());
            tabelPopulat.addKeyListener(new StergereRandAbonat());
            tabelPopulat.addMouseListener(new EditareAbonatClick());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eroare: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ContactsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Metoda de aflare a indexului randului curent selectat.
     *
     * @return randCurent randul curent selectat
     */
    public int indexRandSelectat() {
        int randCurent = tabelPopulat.getSelectedRow();

        /*
         * La afisarea tabelului se foloseste randul selectat. Dupa modificarea
         * datelor, valoarea variabilei "rand" devenea -1 si astfel am folosit
         * variabila statica randSelectat pentru pastarea valorii randului selectat
         */
        if (randCurent == -1) {
            randCurent = randSelectat;
        }

        return randCurent;
    }

    /**
     * Metoda pentru activarea casutelor de input la selectarea prin click
     * a unui rand din tabel.
     * In momentul in care a fost selectat un rand din tabel care contine
     * inregistrarile unui abonat, se citeste id-ul inregistrarii si se
     * selecteaza inregistrarile din baza de date in functie de ID-ul citit.
     * Dupa acest lucru, campurile de adaugare/editare sunt completate
     * automat cu datele din tabel.
     */
    public void selecteazaRand() {
        ResultSet rs = null;
        try {
            int rand = indexRandSelectat();

            String sql = "select * from abonat where id=" + tabelPopulat.getValueAt(rand, 0);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                numeText.setText(rs.getString("nume"));
                prenumeText.setText(rs.getString("prenume"));
                cnpText.setText(rs.getString("cnp"));
                telefonText.setText(rs.getString("telefon"));
                randSelectat = rand;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eroare: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ContactsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    /**
     * Metoda de adaugare abonat
     * Se construieste un obiect nou de tip abonat in care se verifica daca
     * tipul de numar de telefon introdus este mobil sau fix si daca nu incepe
     * cu '07' sau '02' si daca nu are 10 caractere se afiseaza eroare.
     * Dupa ce s-a facut acest lucru, datele sunt introduse(INSERT) in baza
     * de date
     */
    private void adaugareAbonat() {

        Phone numar = null;
        String nume = numeText.getText();
        String prenume = prenumeText.getText();
        String cnp = cnpText.getText();
        String telefon = telefonText.getText();

        if (campuriCompletate()) {

            try {
                numar = nrTel.getPhoneNumberType(telefon);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Eroare: " + e.getMessage());
            }
            String id = "1";
            Contact contactUnic = new Contact(nume, prenume, cnp, numar);
            PreparedStatement ps = null;
            try {
                String adaugaQuery = "INSERT INTO ABONAT(nume,prenume,cnp,telefon)"
                        + "VALUES(?,?,?,?)";
                ps = conn.prepareStatement(adaugaQuery);
                ps.setString(1, contactUnic.getFirstName());
                ps.setString(2, contactUnic.getLastName());
                ps.setString(3, contactUnic.getCnp());
                ps.setString(4, contactUnic.getPhoneNumber().toString());

                int n = ps.executeUpdate();
                if (n > 0) {
                    actualizareTabel();
                    stergereInput();
                    JOptionPane.showMessageDialog(null, "Date salvate cu succes!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Eroare: " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ContactsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Completati toate campurile!");
        }
    }

    /**
     * Metoda pentru cautarea abonatului in functie de nume, prenume, cnp
     * sau telefon.
     * Se citeste din campul de cautare textul introdus si este cautat in baza
     * de date.
     */
    private void cautareAbonat() {
        ResultSet rs = null;
        try {
            String textCautat = cautareText.getText().trim();

            if (!textCautat.equals("")) {
                String sql = "SELECT nume,prenume,cnp,telefon from abonat "
                        + "where nume like '%" + textCautat + "%' "
                        + "or prenume like '%" + textCautat + "%' "
                        + "or cnp like '%" + textCautat + "%' "
                        + "or telefon like '%" + textCautat + "%'";
                String sqlRezultate = "SELECT COUNT(*) as rezultate FROM ( "
                        + sql + " ) as inregistrari";
                stmt = conn.createStatement();

                int rezultate;
                rezultate = 0;
                ResultSet rsRezultate;
                rsRezultate = stmt.executeQuery(sqlRezultate);
                try {
                    rsRezultate.next();
                    rezultate = rsRezultate.getInt(1);
                } catch (SQLException ex) {
                    Logger.getLogger(ContactsController.class.getName()).
                            log(Level.SEVERE, null, ex);
                } finally {
                    if (rsRezultate != null) {
                        rsRezultate.close();
                    }
                }

                rs = stmt.executeQuery(sql);
                if (rezultate == 1) {
                    rs.next();
                    numeText.setText(rs.getString("nume"));
                    prenumeText.setText(rs.getString("prenume"));
                    cnpText.setText(rs.getString("cnp"));
                    telefonText.setText(rs.getString("telefon"));
                } else if (rezultate > 1) {
                    JOptionPane.showMessageDialog(null, "Am gasit : " + rezultate
                            + " rezultate. Cautati dupa telefon sau CNP pentru rezultate unice!");
                } else {
                    JOptionPane.showMessageDialog(null, "Niciun rezultat gasit!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Completati campul de cautare!");
            }
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eroare: " + ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ContactsController.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    /**
     * Metoda pentru stergerea abonatului din tabel si baza de date.
     * Stergerea se face prin selectarea unui rand din tabelul cu abonati.
     */
    private void stergeAbonat() {
        int rand = tabelPopulat.getSelectedRow();
        if (rand == -1) {
            JOptionPane.showMessageDialog(null, "Selectati un abonat pentru a fi sters!");
        } else {
            try {
                int valoareMesaj = JOptionPane.showConfirmDialog(tabelPopulat,
                        "Doriti stergerea abonatului?", "Confirmati stergerea",
                        JOptionPane.YES_NO_OPTION);
                if (valoareMesaj == JOptionPane.YES_OPTION) {
                    String stergeSQL = "DELETE FROM ABONAT "
                            + " WHERE id=" + tabelPopulat.getValueAt(rand, 0);
                    stmt = conn.createStatement();
                    int n = stmt.executeUpdate(stergeSQL);

                    /*
                     * Daca rezultatul al executiei query-ului
                     * stergeSQL este mai mare ca 0 inseamna ca
                     * s-au facut  modificari in baza si trebuie sa se
                     * actualizeze datele  afisate in tabel si sa se stearga
                     * campurile de input.
                     */
                    if (n > 0) {
                        stergereInput();
                        actualizareTabel();
                        aplicatieShareware();
                        JOptionPane.showMessageDialog(null, "Contact sters!");
                    }
                } else if (valoareMesaj == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Abonatul nu a fost sters!");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Eroare: " + ex.getMessage());
            }
        }
    }

    /**
     * Metoda pentru verificare campurilor de adaugare/editare
     *
     * @return true in cazul in care campurile de adaugare sunt completate
     */
    private boolean campuriCompletate() {

        String nume = numeText.getText();
        String prenume = prenumeText.getText();
        String cnp = cnpText.getText();
        String telefon = telefonText.getText();

        return (nume != null && nume.length() > 0) && (prenume != null && prenume.length() > 0)
                && (cnp != null && cnp.length() > 0) && (telefon != null && telefon.length() > 0);
    }

    /**
     * Modificarea datelor unui abonat dupa selectarea/gasirea in tabel
     * Se verifica daca sunt completate cammpurile de adaugare/editare abonat
     * si se face actualizarea in baza de date.
     */
    private void actualizeazaAbonat() {

        if (campuriCompletate()) {
            PreparedStatement ps = null;
            try {
                String adaugaQuery = "UPDATE ABONAT SET nume=?, prenume=?, "
                        + " cnp=? ,telefon=?"
                        + " WHERE id=?";
                int rand = tabelPopulat.getSelectedRow();
                String id = (String) tabelPopulat.getValueAt(rand, 0);
                ps = conn.prepareStatement(adaugaQuery);
                ps.setString(1, numeText.getText());
                ps.setString(2, prenumeText.getText());
                ps.setString(3, cnpText.getText());
                ps.setString(4, telefonText.getText());
                ps.setString(5, id);
                int n = ps.executeUpdate();
                if (n > 0) {
                    stergereInput();
                    actualizareTabel();
                    JOptionPane.showMessageDialog(null, "Date actualizate cu succes!");
                }
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Eroare: " + ex.getMessage());
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ContactsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cautati sau selectati un abonat!");
        }
    }

    /**
     * Metoda de finalizare pentru inchiderea conexiunii la baza de date
     *
     * @throws Throwable Exceptie in cazul in care conexiunea este invalida
     */
    @Override
    protected void finalize() throws Throwable {
        if (conn != null) {
            conn.close();
        }
        super.finalize();

    }


}
