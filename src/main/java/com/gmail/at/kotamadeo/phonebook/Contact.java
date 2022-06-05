package com.gmail.at.kotamadeo.phonebook;

import java.util.Objects;

public class Contact {
    private String surname;
    private String name;
    private String phone;

    public Contact(String surname, String name, String phone) {
        this.surname = surname;
        this.name = name;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        var contact = (Contact) o;
        if (((surname == null) && (name == null)) || ("".equals(surname) && "".equals(name))) {
            return phone.equals(contact.phone);
        }
        if (surname == null || "".equals(surname) || surname.length() == 0) {
            return name.equals(contact.name) && phone.equals(contact.phone);
        } else if (name == null || "".equals(name) || name.length() == 0) {
            return surname.equals(contact.surname) && phone.equals(contact.phone);
        } else {
            return surname.equals(contact.surname) && name.equals(contact.name) && phone.equals(contact.phone);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, phone);
    }

    @Override
    public String toString() {
        if (((surname == null) && (name == null)) || ("".equals(surname) && "".equals(name))) {
            return "Неизвестный номер телефона - " + phone + ".";
        }
        if (surname == null || "".equals(surname) || surname.length() == 0) {
            return name + ": номер телефона - " + phone + ".";
        } else if (name == null || "".equals(name) || name.length() == 0) {
            return surname + ": номер телефона - " + phone + ".";
        } else {
            return surname + " " + name + ": номер телефона - " + phone + ".";
        }
    }
}
