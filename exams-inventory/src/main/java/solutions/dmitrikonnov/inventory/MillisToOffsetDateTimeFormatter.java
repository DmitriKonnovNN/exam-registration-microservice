package solutions.dmitrikonnov.inventory;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Locale;


public class MillisToOffsetDateTimeFormatter implements Formatter<OffsetDateTime> {
    @Override
    public OffsetDateTime parse(String text, Locale locale) throws ParseException {
        long millis = Long.parseLong(text);
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneOffset.UTC);
    }

    @Override
    public String print(OffsetDateTime object, Locale locale) {
        return Long.toString(object.toInstant().toEpochMilli()) ;
    }
}
