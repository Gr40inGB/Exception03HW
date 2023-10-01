package org.gr40in;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogService {

    private final Path LOG_FILE = Path.of("app_errors.log");

    public boolean checkOrCreateDirectory() {
        if (!Files.exists(LOG_FILE)) {
            try {
                Files.createFile(LOG_FILE);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else return true;
    }

}
