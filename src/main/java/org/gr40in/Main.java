package org.gr40in;

import org.gr40in.app.ConsoleApp;
import org.gr40in.fakedatabase.FakeDataBase;
import org.gr40in.model.Human;
import org.gr40in.model.Sex;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        FakeDataBase fakeDataBase = new FakeDataBase();
//        System.out.println(fakeDataBase.checkOrCreateDirectory());
//        System.out.println(fakeDataBase.countOfFiles());
////        List<String> strings = fakeDataBase.getAllLastNames();
////        for (String s :strings){
////            System.out.println(s);
////        }
//        Human human = new Human("Imangulov", "Rishat", "Salavatovitch", LocalDate.of(1986, 6, 16), 89371501503L, Sex.M);
//        Human human2 = new Human("Imangulov", "hfkja", "Salavatovitch", LocalDate.of(1986, 6, 16), 89371501503L, Sex.M);
//        System.out.println(human);
//        System.out.println();
        // imangulov riahst calalanch 16.06.1986 89371501503 m
//        fakeDataBase.writeNewHuman(human);
//        fakeDataBase.writeNewHuman(human2);
        ConsoleApp consoleApp = new ConsoleApp();
        consoleApp.run();

    }
}