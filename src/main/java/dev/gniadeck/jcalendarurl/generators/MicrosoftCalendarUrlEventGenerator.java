package dev.gniadeck.jcalendarurl.generators;

import dev.gniadeck.jcalendarurl.EventDetails;
import lombok.SneakyThrows;

import java.net.URL;
import java.time.format.DateTimeFormatter;

import static dev.gniadeck.jcalendarurl.generators.GeneratorUtils.offsetToUtc;

public abstract class MicrosoftCalendarUrlEventGenerator implements CalendarEventUrlGenerator {

    private static final DateTimeFormatter MICROSOFT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private final String TEMPLATE = getTemplate();


    protected abstract String getTemplate();

    @SneakyThrows
    @Override
    public URL generateUrl(EventDetails eventDetails) {
        StringBuilder urlBuilder = new StringBuilder(TEMPLATE);

        urlBuilder.append("body=");
        urlBuilder.append(eventDetails.description());
        urlBuilder.append("&");
        urlBuilder.append("enddt=");
        urlBuilder.append(offsetToUtc(eventDetails.end()).format(MICROSOFT_FORMATTER));
        urlBuilder.append("&");
        urlBuilder.append("path=%2Fcalendar%2Faction%2Fcompose&rru=addevent&");
        urlBuilder.append("startdt=");
        urlBuilder.append(offsetToUtc(eventDetails.start()).format(MICROSOFT_FORMATTER));
        urlBuilder.append("&subject=");
        urlBuilder.append(eventDetails.title());

        return new URL(urlBuilder.toString());
    }

}
