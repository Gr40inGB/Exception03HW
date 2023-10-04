package org.gr40in;

import org.gr40in.app.ConsoleApp;
import org.gr40in.fakedatabase.FakeDataBase;
import org.gr40in.model.Human;
import org.gr40in.model.Sex;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        Human human = new Human("Imangulov", "Rishat", "Salavatovitch", LocalDate.of(1986, 6, 16), 89371501503L, Sex.M);
//        Human human2 = new Human("Imangulov", "hfkja", "Salavatovitch", LocalDate.of(1986, 6, 16), 89371501503L, Sex.M);

        ConsoleApp consoleApp = new ConsoleApp();
        consoleApp.run();

    }
}