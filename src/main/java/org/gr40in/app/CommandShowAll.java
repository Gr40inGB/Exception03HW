package org.gr40in.app;

public class CommandShowAll extends Commands {
    public CommandShowAll(ConsoleApp app) {
        super("Show all: ", app);
    }

    @Override
    public void run() {
        getApp().showAll();
    }
}
