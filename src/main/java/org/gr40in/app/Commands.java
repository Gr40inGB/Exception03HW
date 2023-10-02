package org.gr40in.app;

public abstract class Commands implements CommandsBehavior {
    private String name;
    private ConsoleApp app;

    public String getName() {
        return name;
    }

    public ConsoleApp getApp() {
        return app;
    }

    public Commands(String name, ConsoleApp app) {
        this.name = name;
        this.app = app;
    }
}
