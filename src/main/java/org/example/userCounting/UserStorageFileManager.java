package org.example.userCounting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class UserStorageFileManager {

    private static final String FILE_PATH = "data/users.txt";

    public static Map<Long, UserData> load() {
        Map<Long, UserData> userMap = new HashMap<>();
        Path path = Paths.get(FILE_PATH);

        if (!Files.exists(path)) return userMap;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    long chatId = Long.parseLong(parts[0]);
                    String dateRegistration = parts[1];
                    int count = Integer.parseInt(parts[2]);
                    userMap.put(chatId, new UserData(dateRegistration, count));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } return userMap;
    }

    public static synchronized void save (Map<Long, UserData> userMap) {
        try {
            Path path = Paths.get(FILE_PATH);
            Files.createDirectories(path.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                for (Map.Entry<Long, UserData> entry : userMap.entrySet()) {
                    writer.write(entry.getKey() + ", " +
                            entry.getValue().getRegistrationDate() + ", " +
                            entry.getValue().getRequestCount());
                    writer.newLine();
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
