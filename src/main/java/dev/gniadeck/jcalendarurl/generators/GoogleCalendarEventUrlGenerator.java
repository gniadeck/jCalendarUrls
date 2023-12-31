package dev.gniadeck.jcalendarurl.generators;

import dev.gniadeck.jcalendarurl.EventDetails;
import lombok.SneakyThrows;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static dev.gniadeck.jcalendarurl.generators.GeneratorUtils.offsetToUtc;


public class GoogleCalendarEventUrlGenerator implements CalendarEventUrlGenerator {

    private static final DateTimeFormatter GOOGLE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
    private static final String TEMPLATE = "https://calendar.google.com/calendar/render?action=TEMPLATE&";

    @SneakyThrows
    @Override
    public URL generateUrl(EventDetails event) {
        StringBuilder urlBuilder = new StringBuilder(TEMPLATE);
        String dates = offsetToUtc(event.start()).format(GOOGLE_FORMATTER) + "/" +
                offsetToUtc(event.end()).format(GOOGLE_FORMATTER);

        urlBuilder.append("dates=");
        urlBuilder.append(dates);
        urlBuilder.append("&");
        urlBuilder.append("details=");
        urlBuilder.append(event.description());
        urlBuilder.append("&");
        urlBuilder.append("text=");
        urlBuilder.append(event.title());

        return new URL(urlBuilder.toString());
    }

}
