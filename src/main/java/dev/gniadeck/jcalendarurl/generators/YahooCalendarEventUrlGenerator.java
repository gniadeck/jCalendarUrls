package dev.gniadeck.jcalendarurl.generators;

import dev.gniadeck.jcalendarurl.EventDetails;
import lombok.SneakyThrows;

import java.net.URL;
import java.time.format.DateTimeFormatter;

import static dev.gniadeck.jcalendarurl.generators.GeneratorUtils.offsetToUtc;

public class YahooCalendarEventUrlGenerator implements CalendarEventUrlGenerator {

    private static final String TEMPLATE = "https://calendar.yahoo.com/?";
    private static final DateTimeFormatter YAHOO_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");

    @SneakyThrows
    @Override
    public URL generateUrl(EventDetails eventDetails) {
        StringBuilder urlBuilder = new StringBuilder(TEMPLATE);

        urlBuilder.append("desc=");
        urlBuilder.append(eventDetails.description());
        urlBuilder.append("&dur=");
        urlBuilder.append("&");
        urlBuilder.append("et=");
        urlBuilder.append(offsetToUtc(eventDetails.end()).format(YAHOO_FORMATTER));
        urlBuilder.append("&in_loc=");
        urlBuilder.append("&st=");
        urlBuilder.append(offsetToUtc(eventDetails.start()).format(YAHOO_FORMATTER));
        urlBuilder.append("&title=");
        urlBuilder.append(eventDetails.title());
        urlBuilder.append("&v=60");

        return new URL(urlBuilder.toString());
    }

}
