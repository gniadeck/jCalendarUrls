package dev.gniadeck.jcalendarurl.generators;

import dev.gniadeck.jcalendarurl.EventDetails;

import java.net.URL;

public interface CalendarEventUrlGenerator {
    URL generateUrl(EventDetails eventDetails);
}
