package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.phonebook.Contact;
import com.gmail.at.kotamadeo.phonebook.MissedCall;
import com.gmail.at.kotamadeo.phonebook.Phonebook;
import com.gmail.at.kotamadeo.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    private final List<Contact> contactsList = new ArrayList<>();
    private final Phonebook phonebook = new Phonebook("моя");
    private final MissedCall missedCall = new MissedCall();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        String input;
        String[] allInput;
        while (true) {
            try {
                printMenu();
                input = scanner.nextLine();
                if ("выход".equalsIgnoreCase(input) || "0".equals(input)) {
                    scanner.close();
                    break;
                } else {
                    var operationNumber = Integer.parseInt(input);
                    switch (operationNumber) {
                        case 1:
                            System.out.println(Utils.ANSI_BLUE + "Чтобы переименовать телефонную книгу введите " +
                                    "новое название" + Utils.ANSI_RESET);
                            input = scanner.nextLine();
                            phonebook.setPhonebookTittle(input);
                            break;
                        case 2:
                            System.out.println(Utils.ANSI_BLUE + "Чтобы добавить контакт в список введите: " +
                                    "фамилию, имя и номер телефона через пробел" + Utils.ANSI_RESET);
                            allInput = scanner.nextLine().split(" ");
                            contactsList.add(new Contact(allInput[0], allInput[1], allInput[2]));
                            printContactsList(contactsList);
                            break;
                        case 3:
                            System.out.println(Utils.ANSI_BLUE + "Чтобы создать группу введите ее название" +
                                    Utils.ANSI_RESET);
                            input = scanner.nextLine();
                            phonebook.createGroup(input);
                            break;
                        case 4:
                            System.out.println(Utils.ANSI_BLUE + "Чтобы добавить контакт в группу введите " +
                                    "название группы и номер контакта через пробел:" + Utils.ANSI_RESET);
                            phonebook.printGroupsList();
                            printContactsList(contactsList);
                            allInput = scanner.nextLine().split(" ", 2);
                            phonebook.addContactToGroup(allInput[0],
                                    contactsList.get(Integer.parseInt(allInput[1]) - 1));
                            break;
                        case 5:
                            System.out.println(Utils.ANSI_BLUE + "Чтобы удалить контакт из группы введите " +
                                    "название группы и номер контакта через пробел:" + Utils.ANSI_RESET);
                            phonebook.printGroupsList();
                            printContactsList(contactsList);
                            allInput = scanner.nextLine().split(" ");
                            phonebook.deleteContactFromPhoneBook(allInput[0],
                                    contactsList.get(Integer.parseInt(allInput[1]) - 1));
                            break;
                        case 6:
                            System.out.println(Utils.ANSI_BLUE + "Чтобы удалить группу контактов " +
                                    "введите название группы:" + Utils.ANSI_RESET);
                            phonebook.printGroupsList();
                            input = scanner.nextLine();
                            phonebook.deleteGroupFromPhoneBook(input);
                            break;
                        case 7:
                            phonebook.printPhoneBook();
                            break;
                        case 8:
                            System.out.println(Utils.ANSI_BLUE + "Чтобы получить список контактов в группе " +
                                    "введите название группы:" + Utils.ANSI_RESET);
                            phonebook.printGroupsList();
                            input = scanner.nextLine();
                            phonebook.contactsInGroupList(input);
                            break;
                        case 9:
                            System.out.println(Utils.ANSI_BLUE + "Введите номер телефона, " +
                                    "чтобы найти контакт в телефонной книге:" + Utils.ANSI_RESET);
                            input = scanner.nextLine();
                            phonebook.findContactByPhoneNumber(input);
                            break;
                        case 10:
                            System.out.println(Utils.ANSI_BLUE + "Введите имя и номер телефона, " +
                                    "чтобы найти контакт в телефонной книге:" + Utils.ANSI_RESET);
                            allInput = scanner.nextLine().split(" ", 2);
                            phonebook.findContactByNameAndPhone(allInput[0], allInput[1]);
                            break;
                        case 11:
                            System.out.println(Utils.ANSI_BLUE + "Введите фамилию и номер телефона, " +
                                    "чтобы найти контакт в телефонной книге:" + Utils.ANSI_RESET);
                            allInput = scanner.nextLine().split(" ", 2);
                            phonebook.findContactBySurnameAndPhone(allInput[0], allInput[1]);
                            break;
                        case 12:
                            System.out.println(Utils.ANSI_BLUE + "Введите фамилию и имя, " +
                                    "чтобы найти контакт в телефонной книге:" + Utils.ANSI_RESET);
                            allInput = scanner.nextLine().split(" ", 2);
                            phonebook.findContactBySurnameAndName(allInput[0], allInput[1]);
                            break;
                        case 13:
                            System.out.println(Utils.ANSI_BLUE + "Чтобы позвонить введите номер телефона:" +
                                    Utils.ANSI_RESET);
                            input = scanner.nextLine();
                            missedCall.addNewMissedCall(LocalDateTime.now(), input);
                            break;
                        case 14:
                            missedCall.printMissedCallsList(phonebook);
                            break;
                        case 15:
                            missedCall.clearMissedCallsList();
                            break;
                        case 16:
                            demonstration();
                            System.out.println(Utils.ANSI_BLUE + "Теперь выберете методы, которые хотите " +
                                    "протестировать:" + Utils.ANSI_RESET);
                            break;
                        default:
                            System.out.println(Utils.ANSI_RED + "Вы ввели неверный номер операции!" + Utils.ANSI_RESET);
                    }
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(Utils.ANSI_RED + "Ошибка ввода!" + Utils.ANSI_RESET);
            }
        }
    }

    private static void printMenu() {
        System.out.println(Utils.ANSI_YELLOW + "Эта программа эмулирует работу телефонной книги!" + Utils.ANSI_RESET);
        System.out.println(Utils.ANSI_PURPLE + "Возможные команды программы:" + Utils.ANSI_RESET);
        System.out.println("0 или выход: выход из программы.");
        System.out.println("1: переименовать название телефонной книги.");
        System.out.println("2: добавить контакт в телефонную книгу.");
        System.out.println("3: добавить группу в телефонную книгу.");
        System.out.println("4: добавить контакт в группу.");
        System.out.println("5: удалить контакт из группы.");
        System.out.println("6: удалить группу вместе с контактами.");
        System.out.println("7: вывести телефонную книгу на экран.");
        System.out.println("8: вывести список контактов в заданной группе на экран.");
        System.out.println("9: найти контакт по номеру телефона.");
        System.out.println("10: найти контакт по имени и номеру телефона.");
        System.out.println("11: найти контакт по фамилии и номеру телефона.");
        System.out.println("12: найти контакт по фамилии и имени.");
        System.out.println("13: позвонить по номеру телефона.");
        System.out.println("14: показать список пропущенных вызовов.");
        System.out.println("15: Очистить список пропущенных вызовов.");
        System.out.println("16: Демонстрация работы.");
    }

    private static void printContactsList(List<Contact> contacts) {
        if (!contacts.isEmpty()) {
            System.out.println(Utils.ANSI_CYAN + "Список контактов:" + Utils.ANSI_RESET);
            for (var i = 0; i < contacts.size(); i++) {
                System.out.printf("%s%s. %s %s%n", Utils.ANSI_PURPLE, (i + 1), contacts.get(i), Utils.ANSI_RESET);
            }
        } else {
            System.out.println(Utils.ANSI_RED + "Список контактов пуст!" + Utils.ANSI_RESET);
        }
    }

    private void demonstration() {
        contactsList.add(new Contact(null, null, "89206537212"));
        contactsList.add(new Contact(null, "Иван", "89157528540"));
        contactsList.add(new Contact("Иванов", null, "89304531577"));
        contactsList.add(new Contact("Иванова", "Маргарита", "89459991215"));
        phonebook.addContactToGroup("Неизвестные", contactsList.get(0));
        phonebook.addContactToGroup("Коллеги", contactsList.get(1));
        phonebook.addContactToGroup("Коллеги", contactsList.get(2));
        phonebook.addContactToGroup("Друзья", contactsList.get(3));
        missedCall.addNewMissedCall(LocalDateTime.of(2022, 5, 13, 16, 30), "89206537212");
        missedCall.addNewMissedCall(LocalDateTime.of(2022, 5, 12, 15, 20), "89206537213");
        missedCall.addNewMissedCall(LocalDateTime.of(2022, 5, 13, 12, 40), "89157528540");
        missedCall.addNewMissedCall(LocalDateTime.of(2022, 5, 13, 14, 10), "89304531577");
        missedCall.addNewMissedCall(LocalDateTime.of(2022, 5, 12, 20, 00), "89459991215");
    }
}
