package org.gr40in.model;

import org.gr40in.fakedatabase.LocalDateTypeAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HumanService {
    public static final int ARGUMENTS_COUNT = 6;

    public static Human parseToHuman(String stringData) {
        String lastName;
        String firstName;
        String patronymic;
        LocalDate birthDate;
        Long phoneNumber;
        Sex sex;


        List<String> dataList = new ArrayList<>(Arrays.asList(stringData.split("\s+")));
        int dataLength = dataList.size();
        if (dataLength != ARGUMENTS_COUNT)
            System.out.println("There are " +
                    (dataLength < ARGUMENTS_COUNT ? "not enough" : "too much") +
                    " arguments in your data! Need " +
                    ARGUMENTS_COUNT + ". You entered " + dataLength + "!");

        Human tempLink = new Human();
        for (String s : dataList) {
            if (s.matches("[a-zA-Zа-яА-Я]+") && s.length() > 1) { // ФИО
                if (tempLink.getLastName().isEmpty()) tempLink.setLastName(s);
                else if (tempLink.getFirstName().isEmpty()) tempLink.setFirstName(s);
                else if (tempLink.getPatronymic().isEmpty()) tempLink.setPatronymic(s);
            } else if (s.matches("[mMfF]")) {
                tempLink.setSex(s.equalsIgnoreCase("f") ? Sex.F : Sex.M);
            } else if (s.matches("(0[1-9]|[12][0-9]|3[01])[-\\/.](0[1-9]|1[0-2])[-\\/.](19[0-9][0-9]|20[0-9][0-9])")) {
                System.out.println(validLocalData(s));
            }

        }


        return null;
    }

    static boolean validLocalData(String stringData) {
//        String[] dayMonthYear = stringData.replaceAll("[-\\/.]"," ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // create a LocalDate object and
        LocalDate lt = LocalDate.parse(stringData.replaceAll("[-\\/.]","."), formatter);
        if (lt.isAfter(LocalDate.now())){
            System.out.println("Data is not correct!");
        }
        System.out.println(lt.format(formatter));
        return true;
    }

    public HumanService() {

    }
}
