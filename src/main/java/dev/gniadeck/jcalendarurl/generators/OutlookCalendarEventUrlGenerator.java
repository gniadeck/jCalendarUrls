package dev.gniadeck.jcalendarurl.generators;

public class OutlookCalendarEventUrlGenerator extends MicrosoftCalendarUrlEventGenerator {
    @Override
    protected String getTemplate() {
        return "https://outlook.live.com/calendar/0/deeplink/compose?allday=false&";
    }
}
