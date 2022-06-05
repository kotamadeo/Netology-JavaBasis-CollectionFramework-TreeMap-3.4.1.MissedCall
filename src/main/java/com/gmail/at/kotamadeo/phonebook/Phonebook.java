package com.gmail.at.kotamadeo.phonebook;

import com.gmail.at.kotamadeo.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phonebook {
    private String phonebookTittle;
    private final Map<String, List<Contact>> phonebook = new HashMap<>();

    public Phonebook(String phonebookTittle) {
        this.phonebookTittle = phonebookTittle;
    }

    public void setPhonebookTittle(String phonebookTittle) {
        this.phonebookTittle = phonebookTittle;
    }

    public void createGroup(String tittle) {
        if (tittle != null || !"".equals(tittle)) {
            List<Contact> contactList = new ArrayList<>();
            phonebook.put(tittle, contactList);
            System.out.printf("%sГруппа \"%s\" успешно добавлена в телефонную книгу \"%s\".%s%n",
                    Utils.ANSI_GREEN, tittle, phonebookTittle, Utils.ANSI_RESET);
        } else {
            System.out.printf("%sПроизошла ошибка! Невозможно добавить группу \"%s\" в телефонную книгу \"%s\".%s%n",
                    Utils.ANSI_RED, tittle, phonebookTittle, Utils.ANSI_RESET);
        }
    }

    private boolean isContainsGroupTittleInPhonebookGroups(String tittle) {
        return phonebook.containsKey(tittle);
    }

    private boolean isContainsContactInGroup(String tittle, Contact contact) {
        return isContainsGroupTittleInPhonebookGroups(tittle) && phonebook.get(tittle).contains(contact);
    }

    public void printGroupsList() {
        if (!phonebook.isEmpty()) {
            var counter = 0;
            System.out.println(Utils.ANSI_PURPLE + "Список доступных групп:" + Utils.ANSI_RESET);
            for (String string : phonebook.keySet()) {
                counter++;
                System.out.printf("%s%s. %s.%s%n", Utils.ANSI_CYAN, counter, string, Utils.ANSI_RESET);
            }
        } else {
            System.out.println(Utils.ANSI_RED + "Список групп пуст!" + Utils.ANSI_RESET);
        }
    }

    public void addContactToGroup(String tittle, Contact contact) {
        if (isContainsGroupTittleInPhonebookGroups(tittle) && !isContainsContactInGroup(tittle, contact)) {
            phonebook.get(tittle).add(contact);
            System.out.printf("%sКонтакт %s успешно добавлен в группу \"%s\" телефонной книги \"%s\"!%s%n",
                    Utils.ANSI_CYAN, contact, tittle, phonebookTittle, Utils.ANSI_RESET);
        } else if (!isContainsGroupTittleInPhonebookGroups(tittle) && tittle != null && !"".equals(tittle) &&
                !isContainsContactInGroup(tittle, contact)) {
            System.out.printf("%sГруппы \"%s\" не оказалось в телефонной книге \"%s\", но %s",
                    Utils.ANSI_YELLOW, tittle, phonebookTittle, Utils.ANSI_RESET);
            createGroup(tittle);
            phonebook.get(tittle).add(contact);
        } else {
            System.out.printf("%sК сожалению, невозможно добавить %s в телефонную книгу \"%s\"!%s%n",
                    Utils.ANSI_RED, contact, phonebookTittle, Utils.ANSI_RESET);
        }
    }

    public void contactsInGroupList(String tittle) {
        if (phonebook.containsKey(tittle)) {
            List<Contact> contactsList = phonebook.get(tittle);
            if (!contactsList.isEmpty()) {
                System.out.printf("%sСписок контактов в группе \"%s\":%s%n",
                        Utils.ANSI_PURPLE, tittle, Utils.ANSI_RESET);
                for (Contact contact : contactsList) {
                    System.out.println(Utils.ANSI_CYAN + contact + Utils.ANSI_RESET);
                }
            } else {
                System.out.printf("%sГруппа \"%s\" пуста!%s%n", Utils.ANSI_RED, tittle, Utils.ANSI_RESET);
            }
        } else {
            System.out.printf("%sГруппа \"%s\" не найдена в телефонной книге \"%s\"!%s%n",
                    Utils.ANSI_RED, tittle, phonebookTittle, Utils.ANSI_RESET);
        }
    }

    public void printPhoneBook() {
        if (!phonebook.isEmpty()) {
            for (String string : phonebook.keySet()) {
                contactsInGroupList(string);
                Utils.printDelim();
            }
        } else {
            System.out.println(Utils.ANSI_RED + "Телефонная книга пуста!" + Utils.ANSI_RESET);
        }
    }

    public void deleteContactFromPhoneBook(String tittle, Contact contact) {
        if (!phonebook.isEmpty() && phonebook.containsKey(tittle)) {
            List<Contact> contacts = new ArrayList<>();
            contacts.add(contact);
            phonebook.remove(tittle, contacts);
        }
    }

    public void deleteGroupFromPhoneBook(String tittle) {
        if (!phonebook.isEmpty()) {
            phonebook.entrySet().removeIf(entry -> entry.getKey().equals(tittle));
        }
    }

    public Contact findContactByPhoneNumber(String phone) {
        for (List<Contact> contacts : phonebook.values()) {
            for (Contact contact : contacts) {
                if (contact.getPhone().equals(phone)) {
                    System.out.printf("%sКонтакт найден - %s%s%n", Utils.ANSI_GREEN, contact, Utils.ANSI_RESET);
                    return contact;
                }
            }
        }
        System.out.printf("%sКонтакт по номеру - %s. не найден!%s%n", Utils.ANSI_RED, phone, Utils.ANSI_RESET);
        return new Contact("Неизвестно", null, phone);
    }

    public void findContactByNameAndPhone(String name, String phone) {
        for (List<Contact> contacts : phonebook.values()) {
            for (Contact contact : contacts) {
                if (contact.getName() != null && contact.getName().equalsIgnoreCase(name) &&
                        contact.getPhone().equals(phone)) {
                    System.out.printf("%sКонтакт найден - %s%s%n", Utils.ANSI_GREEN, contact, Utils.ANSI_RESET);
                    return;
                }
            }
        }
        System.out.printf("%sКонтакт по имени и номеру телефона - %s %s. не найден!%s%n",
                Utils.ANSI_RED, name, phone, Utils.ANSI_RESET);
    }

    public void findContactBySurnameAndPhone(String surname, String phone) {
        for (List<Contact> contacts : phonebook.values()) {
            for (Contact contact : contacts) {
                if (contact.getSurname() != null && contact.getSurname().equalsIgnoreCase(surname) &&
                        contact.getPhone().equals(phone)) {
                    System.out.printf("%sКонтакт найден - %s%s%n", Utils.ANSI_GREEN, contact, Utils.ANSI_RESET);
                    return;
                }
            }
        }
        System.out.printf("%sКонтакт по фамилии и номеру телефона - %s %s. не найден!%s%n",
                Utils.ANSI_RED, surname, phone, Utils.ANSI_RESET);
    }

    public void findContactBySurnameAndName(String surname, String name) {
        for (List<Contact> contacts : phonebook.values()) {
            for (Contact contact : contacts) {
                if (contact.getSurname() != null && contact.getName() != null &&
                        contact.getSurname().equalsIgnoreCase(surname) && contact.getName().equalsIgnoreCase(name)) {
                    System.out.printf("%sКонтакт найден - %s%s%n", Utils.ANSI_GREEN, contact, Utils.ANSI_RESET);
                    return;
                }
            }
        }
        System.out.printf("%sКонтакт по фамилии и имени - %s %s. не найден!%s%n",
                Utils.ANSI_RED, surname, name, Utils.ANSI_RESET);
    }
}
