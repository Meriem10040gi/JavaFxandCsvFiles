package org.example.gestionincidents.Utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConverteser {

    public String convert(Long temps){
    Instant instant = Instant.ofEpochMilli(temps);
    ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDate = dateTime.format(formatter);
    return formattedDate;
}
}
