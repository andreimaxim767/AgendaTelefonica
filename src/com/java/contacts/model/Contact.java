package com.java.contacts.model;

public class Contact {

    private String firstName;
    private String lastName;
    private String cnp;
    private Phone phoneNumber;

    public Contact(String contactFirstName, String contactLastName, String contactCNP, Phone contactPhone) {
        firstName = contactFirstName;
        lastName = contactLastName;
        cnp = contactCNP;
        phoneNumber = contactPhone;

    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCnp() {
        return cnp;
    }

    public Phone getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public void setPhoneNumber(Phone phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null) return false;
        if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) return false;
        if (cnp != null ? !cnp.equals(contact.cnp) : contact.cnp != null) return false;
        return phoneNumber != null ? phoneNumber.equals(contact.phoneNumber) : contact.phoneNumber == null;

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + cnp.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Contact [firstName=" + getFirstName() + ", lastName=" + getLastName() + ", cnp=" + getCnp()
                + ", phoneNumber=" + getPhoneNumber() + "]\n";
    }

}