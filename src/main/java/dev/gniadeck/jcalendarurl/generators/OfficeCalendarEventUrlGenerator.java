package dev.gniadeck.jcalendarurl.generators;

public class OfficeCalendarEventUrlGenerator extends MicrosoftCalendarUrlEventGenerator {

    @Override
    protected String getTemplate() {
        return "https://outlook.office.com/calendar/0/deeplink/compose?allday=false&";
    }

}
