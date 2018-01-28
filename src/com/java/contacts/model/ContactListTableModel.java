package com.java.contacts.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ContactListTableModel extends AbstractTableModel {

    private List<Contact> contactList;
    private String[] columnsTitles = {"Id", "First Name", "Last Name", "CNP", "Phone"};

    public ContactListTableModel(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getRowCount() {
        return contactList.size();
    }

    @Override
    public int getColumnCount() {
        return columnsTitles.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnsTitles[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact contact = contactList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                return contact.getFirstName();
            case 2:
                return contact.getLastName();
            case 3:
                return contact.getCnp();
            case 4:
                return contact.getPhoneNumber();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Contact contact = contactList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                contact.setFirstName((String) aValue);
                break;
            case 2:
                contact.setLastName((String) aValue);
                break;
            case 3:
                contact.setCnp((String) aValue);
                break;
            case 4:
                contact.setPhoneNumber(PhoneNumberType.getPhoneNumberType((String) aValue));
                break;
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public Contact getContactAt(int selectedRow) {
        return contactList.get(selectedRow);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnsTitles[columnIndex].getClass();
    }
}
