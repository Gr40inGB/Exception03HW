package org.gr40in.model;

import org.gr40in.HumanParsingException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HumanService {
    public static final int ARGUMENTS_COUNT = 6;

    public static Human parseToHuman(String stringData) throws HumanParsingException {
        List<String> dataList = new ArrayList<>(Arrays.asList(stringData.split("\s+")));

        int dataLength = dataList.size();
        if (dataLength != ARGUMENTS_COUNT)
            throw new HumanParsingException("There are " +
                    (dataLength < ARGUMENTS_COUNT ? "not enough" : "too much") +
                    " arguments in your data! Need " +
                    ARGUMENTS_COUNT + ". You entered " + dataLength + "!");

        Human tempHumanLink = new Human();

        for (String s : dataList) {
            if (s.matches("[a-zA-Zа-яА-Я]+") && s.length() > 1 && tempHumanLink.getLastName().isEmpty())
                tempHumanLink.setLastName(s);
            else if (s.matches("[a-zA-Zа-яА-Я]+") && s.length() > 1 && tempHumanLink.getFirstName().isEmpty())
                tempHumanLink.setFirstName(s);
            else if (s.matches("[a-zA-Zа-яА-Я]+") && s.length() > 1 && tempHumanLink.getPatronymic().isEmpty())
                tempHumanLink.setPatronymic(s);
            else if
            (s.matches("[mMfF]")) tempHumanLink.setSex(s.equalsIgnoreCase("f") ? Sex.Female : Sex.Male);
            else if
            (s.matches("(0[1-9]|[12][0-9]|3[01])[-\\/.](0[1-9]|1[0-2])[-\\/.](19[0-9][0-9]|20[0-9][0-9])")) {
                tempHumanLink.setBirthDate(getValidLocalData(s));
            } else if (s.matches("(\\+?)([8|7|9])(-?)\\d{3}(-?)" +
                    "\\d{1}(-?)\\d{1}(-?)\\d{1}(-?)\\d{1}(-?)\\d{1}(-?)\\d{1}(-?)\\d{1}")) {
                tempHumanLink.setPhoneNumber(getValidPhone(s));
            } else throw new HumanParsingException("Can't parse this data: " + s);
        }
        return tempHumanLink;
    }

    public static boolean allHumanValuesGood(Human human) {
        return !human.getLastName().isEmpty() &&
                !human.getFirstName().isEmpty() &&
                !human.getPatronymic().isEmpty() &&
                human.getBirthDate() != null &&
                human.getPhoneNumber() != null &&
                human.getSex() != null;
    }

    public static String detailingHumanDataErrors(Human human) {
        StringBuilder stringBuilder = new StringBuilder("In input data not found: ");
        if (human.getLastName().isEmpty()) stringBuilder.append("Lastname ");
        if (human.getFirstName().isEmpty()) stringBuilder.append("FirstName ");
        if (human.getPatronymic().isEmpty()) stringBuilder.append("Patronymic ");
        if (human.getBirthDate() == null) stringBuilder.append("BirthDate ");
        if (human.getPhoneNumber() == null) stringBuilder.append("PhoneNumber ");
        if (human.getSex() == null) stringBuilder.append("Sex ");
        return stringBuilder.toString();
    }

    static LocalDate getValidLocalData(String stringData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(stringData.replaceAll("[-\\/.]", "."), formatter);
        if (localDate.isAfter(LocalDate.now()))
            throw new HumanParsingException("Found Data " + stringData + " is not correct!");

        return localDate;
    }

    static Long getValidPhone(String stringData) {
        String digitString = stringData.replaceAll("[-\\/.]", "");
        return Long.parseLong(digitString);
    }

    public HumanService() {

    }
}
