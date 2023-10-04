package org.gr40in.app;

import org.gr40in.HumanParsingException;
import org.gr40in.fakedatabase.FakeDataBase;
import org.gr40in.model.Human;
import org.gr40in.model.HumanService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private List<Commands> menu;
    private FakeDataBase dataBase;
    private boolean run;

    Scanner scanner = new Scanner(System.in);

    public List<Commands> getMenu() {
        return this.menu;
    }

    public FakeDataBase getDataBase() {
        return this.dataBase;
    }

    public boolean isRun() {
        return run;
    }

    public void enterNew() {
        System.out.print("Please enter new human:\n" +
                "Lastname FirstName Patronymic, BirdDate - like 16-06-1986 or 16.06.1986, \n" +
                "Phone number - like 8-937-150-1505 or +79371501505 and sex - m or M for Male or f F for Female\n" +
                "example: Kats Maxim Evgenievich 23.12.1984 +7-937-150-1505 M\n>>>>> ");
        String userInput = scanner.nextLine();
        Human human = new Human();

        try {
            human = HumanService.parseToHuman(userInput);
            if (HumanService.allHumanValuesGood(human)) this.dataBase.writeNewHuman(human);
            else System.out.println(HumanService.detailingHumanDataErrors(human));
        } catch (HumanParsingException e) {
            System.out.println(e.getMessage());
        }

    }


    public void showAll() {

    }

    public void exit() {
        System.out.println("buy buy ;)");
        this.run = false;
    }

    public void run() {
        while (this.isRun()) {
            showMenu();
            Commands selected = this.getMenu().
                    get(inputNumber("\nEnter selected command number: ", this.getMenu().size()) - 1);
            selected.run();
        }
    }

    private int inputNumber(String welcome, int biggest) {
        boolean inputError = true;
        int rezult = 0;
        while (inputError) {
            System.out.print(welcome);
            try {
                rezult = Integer.parseInt(scanner.nextLine());
                if (rezult <= biggest && rezult > 0) {
                    inputError = false;
                } else System.out.print("Choose between 1-" + biggest + "\n>>>> ");
            } catch (NumberFormatException nfe) {
                System.out.println("error - try again:");
            }
        }
        return rezult;
    }

    public ConsoleApp() {
        this.run = true;
        this.dataBase = new FakeDataBase();
        this.menu = new ArrayList<>();
        menu.add(new CommandInputNew(this));
        menu.add(new CommandShowAll(this));
        menu.add(new CommandExit(this));

    }

    private void showMenu() {
        System.out.println("Main menu: ");
        for (int i = 0; i < this.menu.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + menu.get(i).getName());
        }
    }
}
