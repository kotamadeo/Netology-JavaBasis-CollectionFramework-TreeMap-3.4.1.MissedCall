# **Задача № 1 Пропущенный вызов**

## **Цель**:
1. Создать программу для хранения пропущенных звонков, используя уже изученные коллекции, в том числе коллекцию ```TreeMap```. Программа должна хранить список пропущенных вызовов в формате: дата и время звонка и номер.
2. Необходимо реализовать следующие классы:
* **Contact** - описывающий контакт;
* **Phonebook** - описывающий телефонную книгу из прошлого задания и реализовать метод для поиска контакта по номеру телефона.
* **MissedCall** - описывающий пропущенный вызов.

### *Пример*:
``` Пример 1
1. Возьмите классы с прошлого задания про справочник. Доработайте их для новых требований

2. Для хранения пропущенных вызовов использовать TreeMap, где ключ — время пропущенного вызова, а значение — номер телефона. Для хранения времени звонка подойдет тип данных LocalDateTime — у него уже определен метод compareTo для использования в коллекции TreeMap. Нужно также создать отдельный класс для хранения и работы с этой коллекцией MissedCall.java.
Map<LocalDateTime, String> missedCalls = new TreeMap<>();

3. Создайте класс Main и продемонстрируйте в нем работу ваших классов.
```

### **Моя реализация**:
1. Реализация осуществлена в парадигме ООП.
2. Создал структуру классов:

* **Program** - класс, отвечающий за запуск программы, путем инициирования метода *start()* с инициированием внутри себя
  вспомогательных ```void``` методов: 
  * *printMenu()* - выводит меню команд программы на экран;
  * *printContactsList()* - выводит спиок контактов на экран;
  * *demonstration()* - демонстрация работы программы. 

#### Класс **Program**:
``` java
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
```

* **MissedCall** - класс, описывающий пропущенный вызов. имеет 3 ```void``` метода: *addNewMissedCall()* - добавляющий новый пропущенный вызов, *printMissedCallsList()* - выводящий на экран список пропущенных вызов, *clearMissedCallsList()* - очищающий список пропущенных вызовов.

#### Класс **MissedCall**:
``` java   
public class MissedCall {
    private final Map<LocalDateTime, String> missedCalls = new TreeMap<>();
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("MM.dd.yyyy HH:mm");
    public void addNewMissedCall(LocalDateTime time, String number) {
        missedCalls.put(time, number);
    }

    public void printMissedCallsList(Phonebook phonebook) {
        if (!missedCalls.isEmpty()) {
            System.out.println(Utils.ANSI_PURPLE + "Список пропущенных вызовов:" + Utils.ANSI_RESET);
            var stringBuilder = new StringBuilder();
            for (Map.Entry<LocalDateTime, String> entry : missedCalls.entrySet()) {
                stringBuilder.append(format.format(entry.getKey()))
                .append(" ")
                .append(phonebook.findContactByPhoneNumber(entry.getValue()))
                .append("\n");
            }
            System.out.println(Utils.ANSI_RED + stringBuilder + Utils.ANSI_RESET);
        }
    }

    public void clearMissedCallsList() {
        if (missedCalls.isEmpty()) {
            System.out.println(Utils.ANSI_RED + "Список пропущенных вызовов пуст!" + Utils.ANSI_RESET);
        } else {
            missedCalls.clear();
            System.out.println(Utils.ANSI_GREEN + "Список пропущенных вызовов очищен!" + Utils.ANSI_RESET);
        }
    }
}
```

* **Phonebook** - класс, описывающий телефонную книгу. Методы описаны в ```JavaDoc```.

#### Класс **Phonebook**:
``` java
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
```
2. Использовал кодирование цвета текста (ANSI).

#### Класс **Utils**:
``` java
public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printDelim() {
        System.out.println(ANSI_GREEN + "*********************************************" + ANSI_RESET);
    }
}
```

3. Использовал ```try-catch```, чтобы избежать падение программы в исключения.

#### Метод *main()* в классе **Main**:
``` java
public class Main {
    public static void main(String[] args) {
        var program = new Program();
        program.start();
    }
}
```

## *Вывод в консоль*:

* меню:
``` 
Эта программа эмулирует работу телефонной книги!
Возможные команды программы:
0 или выход: выход из программы.
1: переименовать название телефонной книги.
2: добавить контакт в телефонную книгу.
3: добавить группу в телефонную книгу.
4: добавить контакт в группу.
5: удалить контакт из группы.
6: удалить группу вместе с контактами.
7: вывести телефонную книгу на экран.
8: вывести список контактов в заданной группе на экран.
9: найти контакт по номеру телефона.
10: найти контакт по имени и номеру телефона.
11: найти контакт по фамилии и номеру телефона.
12: найти контакт по фамилии и имени.
13: позвонить по номеру телефона.
14: показать список пропущенных вызовов.
15: Очистить список пропущенных вызовов.
16: Демонстрация работы.

```

* демонстрация работы:
``` 
16
Теперь выберете методы, которые хотите протестировать:

13
Чтобы позвонить введите номер телефона:
89157528504

14
Список пропущенных вызовов:
Контакт по номеру - 89206537213. не найден!
Контакт найден - Иванова Маргарита: номер телефона - 89459991215.
Контакт найден - Иван: номер телефона - 89157528540.
Контакт найден - Иванов: номер телефона - 89304531577.
Контакт найден - Неизвестный номер телефона - 89206537212.
Контакт по номеру - 89157528504. не найден!
05.12.2022 15:20 Неизвестно: номер телефона - 89206537213.
05.12.2022 20:00 Иванова Маргарита: номер телефона - 89459991215.
05.13.2022 12:40 Иван: номер телефона - 89157528540.
05.13.2022 14:10 Иванов: номер телефона - 89304531577.
05.13.2022 16:30 Неизвестный номер телефона - 89206537212.
06.05.2022 15:56 Неизвестно: номер телефона - 89157528504.

15
Список пропущенных вызовов очищен!

14
<так как список вызов пуст, то ничего не выводится на экран>
```