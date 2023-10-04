package org.gr40in.fakedatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.gr40in.model.Human;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FakeDataBase {
    private final String DIRECTORY_NAME = "AllLastNamesIn";
    private final Path BASE_PATH = Path.of(DIRECTORY_NAME);
    Set<String> allLastNames;
    Set<Human> allHumans;

    public FakeDataBase() {
        this.allLastNames = new HashSet<>();
        this.allHumans = new HashSet<>();
        if (isBaseDirectoryExists() && countOfFiles() > 0) {
            this.allLastNames = readAllLastNames();

        }
    }

    public void writeNewHuman(Human human) {
        this.updateLastNameSet();
        if (!this.allLastNames.contains(human.getLastName())) {
            System.out.println(createLastNameFile(human.getLastName()));
        }
        Gson humanGson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).setPrettyPrinting().create();
        String humanJSON = humanGson.toJson(human);

        try {
            Files.write(Path.of(DIRECTORY_NAME, human.getLastName()), humanJSON.getBytes(), StandardOpenOption.APPEND);
            System.out.println("Human " + human + " was written in \"database\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateLastNameSet() {
        this.allLastNames = getAllLastNames();
    }

    private boolean createLastNameFile(String lastName) {

        if (!Files.exists(Paths.get(DIRECTORY_NAME, lastName))) {
            try {
                Files.createFile(Paths.get(DIRECTORY_NAME, lastName));
                this.allLastNames.add(lastName);
                System.out.println("File " + lastName + " was created");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else return true;
    }

    public Long countOfFiles() {
        try (Stream<Path> stream = Files.list(BASE_PATH)) {
            return stream.count();
        } catch (IOException e) {
            return 0L;
        }
    }

    private Set<String> readAllLastNames() {
        Set<String> newList = new HashSet<>();
        try (Stream<Path> files = Files.walk(BASE_PATH)) {
            newList = files.filter(Files::isRegularFile)
                    .map(Objects::toString)
                    .map(s -> s.replace(BASE_PATH.toString() + "\\", ""))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            System.out.println("we cant read files!");
        }
        return newList;
    }

    public Set<String> getAllLastNames() {
        return allLastNames;
    }

    public boolean checkOrCreateDirectory() {
        if (!Files.exists(BASE_PATH)) {
            try {
                Files.createDirectory(BASE_PATH);
                return true;
            } catch (IOException e) {
                System.out.println("can't create directory" + BASE_PATH);
                return false;
            }
        } else return true;
    }

    private boolean isBaseDirectoryExists() {
        return Files.exists(BASE_PATH);
    }
}
