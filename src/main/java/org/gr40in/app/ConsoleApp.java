package org.gr40in.app;

import org.gr40in.fakedatabase.FakeDataBase;
import org.gr40in.model.Human;
import org.gr40in.model.HumanService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    List<Commands> menu;
    FakeDataBase dataBase;
    boolean run;

    Scanner scanner = new Scanner(System.in);

    public List<Commands> getMenu() {
        return menu;
    }

    public FakeDataBase getDataBase() {
        return dataBase;
    }

    public boolean isRun() {
        return run;
    }

    public void enterNew() {
        System.out.print("Please enter new human: ");
        String userInput = scanner.nextLine();
        System.out.println(HumanService.parseToHuman(userInput));
    }



    public void showAll() {

    }

    public void exit() {
        this.run = false;
    }

    public void run() {
        while (this.isRun()) {
            showMenu();
            Commands selected = this.getMenu().
                    get(inputNumber("Enter selected command number: ", this.getMenu().size()) - 1);
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
        for (int i = 0; i < this.menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i).getName());
        }
    }
}
