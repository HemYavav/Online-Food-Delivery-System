package com.esewa.shared;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LastModifiedDate {
    public static String getLastModifiedDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);
    }

}
