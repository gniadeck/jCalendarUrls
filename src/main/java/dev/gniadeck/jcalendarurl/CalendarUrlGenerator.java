package dev.gniadeck.jcalendarurl;

import dev.gniadeck.jcalendarurl.generators.*;

import java.net.URL;
import java.util.Map;

import static dev.gniadeck.jcalendarurl.CalendarType.*;

public class CalendarUrlGenerator {

    private final Map<CalendarType, CalendarEventUrlGenerator> generators = Map.of(
            GOOGLE_CALENDAR, new GoogleCalendarEventUrlGenerator(),
            MICROSOFT_OFFICE, new OfficeCalendarEventUrlGenerator(),
            MICROSOFT_OUTLOOK, new OutlookCalendarEventUrlGenerator(),
            YAHOO, new YahooCalendarEventUrlGenerator()
    );

    public URL generate(CalendarType type, EventDetails eventDetails) {
        return generators.get(type).generateUrl(eventDetails);
    }

    public CalendarUrls generate(EventDetails eventDetails) {
        var google = generate(GOOGLE_CALENDAR, eventDetails);
        var office = generate(MICROSOFT_OFFICE, eventDetails);
        var outlook = generate(MICROSOFT_OUTLOOK, eventDetails);
        var yahoo = generate(YAHOO, eventDetails);
        return new CalendarUrls(google, office, outlook, yahoo);
    }

}
