package com.sparta.the300.loggers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatting extends Formatter {

    @Override
    public String format(LogRecord record) {
        return (LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                + " " + record.getSourceClassName()
                + " " + record.getSourceMethodName()
                + " " + record.getLevel()
                + " " + record.getMessage()
                + "\n";
    }
}
