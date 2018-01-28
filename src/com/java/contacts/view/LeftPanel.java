package com.java.contacts.view;

import com.java.contacts.observers.LeftPanelObserver;
import com.java.contacts.model.Contact;
import com.java.contacts.model.ContactListTableModel;
import com.java.contacts.model.ContactsModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class LeftPanel extends JPanel {

    private JTextField searchField;
    private JTable contactsTable;
    private ContactsModel contactsModel;
    private List<LeftPanelObserver> leftPanelObserverList = new ArrayList<>();

    public LeftPanel(ContactsModel contactsModel) {
        this.contactsModel = contactsModel;

        searchField = new JTextField("Search");
        contactsTable = new JTable();

        init();
        initTabelModel();
        initSearchFieldFiltering();
    }

    private void init() {
        setSize(new Dimension(400, 600));
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 100;
        add(searchField, gbc);

        gbc.ipadx = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(contactsTable), gbc);
    }

    private void initTabelModel() {
        contactsTable.setAutoCreateRowSorter(true);
        contactsTable.setModel(contactsModel.getContactListTableModel());
        contactsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                notifyLeftPanelObservers();
            }
        });
    }

    private void initSearchFieldFiltering() {
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(contactsModel.getContactListTableModel());
        contactsTable.setRowSorter(sorter);

        searchField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                searchFieldInputChanged(sorter);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchFieldInputChanged(sorter);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

        });
    }

    private void searchFieldInputChanged(TableRowSorter<TableModel> sorter) {
        String text = searchField.getText();
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

    public void addContactToModel(Contact contact) {
        contactsModel.addContact(contact);
    }


    public void deleteContactFromModel() {
        int selectedContactPosition = contactsTable.getSelectedRow();
        if(selectedContactPosition != -1) {
            contactsModel.removeAbonatEntryAt(selectedContactPosition);
        }

    }

    public void registerObserver(LeftPanelObserver leftPanelObserver) {
        leftPanelObserverList.add(leftPanelObserver);
    }

    public void removeObserver(LeftPanelObserver leftPanelObserver) {
        if(leftPanelObserverList.contains(leftPanelObserver)) {
            leftPanelObserverList.remove(leftPanelObserver);
        }
    }

    public void notifyLeftPanelObservers() {
        for(LeftPanelObserver observer : leftPanelObserverList) {
            Optional<Contact> maybeContactSelected = getSelectedContactFromTable();
            if(maybeContactSelected.isPresent()) {
                observer.selectedContactDidChanged(maybeContactSelected.get());
            }
        }
    }

    public Optional<Contact> getSelectedContactFromTable() {
        int selectedRow = contactsTable.getSelectedRow();
        if(selectedRow != -1) {
            ContactListTableModel model = (ContactListTableModel) contactsTable.getModel();
            Contact selectedContact = model.getContactAt(selectedRow);
            return Optional.of(selectedContact);
        } else {
            return Optional.empty();
        }
    }

}
