package com.gmail.at.kotamadeo.phonebook;

import com.gmail.at.kotamadeo.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

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
