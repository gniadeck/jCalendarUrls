package dev.gniadeck.jcalendarurl.generators;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class GeneratorUtils {

    static LocalDateTime offsetToUtc(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }

}
