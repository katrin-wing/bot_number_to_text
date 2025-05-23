package org.example.userCounting;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserStorageService {

    private static final Map<Long, UserData> userMap = new ConcurrentHashMap<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        userMap.putAll(UserStorageFileManager.load());
    }

    public static void addRequest(long chatId) {
        userMap.compute(chatId, (id, data) -> {
            if (data == null) {
                data = new UserData(dateFormat.format(new Date()), 1);
            } else {
                data.incrementRequestCount();
            }
            UserStorageFileManager.save(userMap);
            return data;
        });
    }

    public static UserData getUserData(long chatId) {
        return userMap.get(chatId);
    }
}
