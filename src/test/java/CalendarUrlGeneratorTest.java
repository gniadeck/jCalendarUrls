import dev.gniadeck.jcalendarurl.CalendarUrlGenerator;
import dev.gniadeck.jcalendarurl.EventDetails;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static dev.gniadeck.jcalendarurl.CalendarType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarUrlGeneratorTest {

    private final CalendarUrlGenerator calendarUrlGenerator = new CalendarUrlGenerator();
    private final EventDetails testEvent = getForDates(
            ZonedDateTime.of(2019, 11, 23, 9, 0, 0, 0, ZoneId.of("Europe/Warsaw")),
            ZonedDateTime.of(2019, 11, 23, 11, 0, 0, 0, ZoneId.of("Europe/Warsaw")));

    @Test
    public void generateGoogleCalendarUrlShouldGenerateGCUrlWhenWinterTime() {
        var testEvent = getForDates(ZonedDateTime.of(2020, 12, 10, 5, 0, 0, 0, ZoneId.of("Europe/Warsaw")),
                ZonedDateTime.of(2020, 12, 10, 9, 0, 0, 0, ZoneId.of("Europe/Warsaw")));

        assertEquals("https://calendar.google.com/calendar/render?action=TEMPLATE&dates=20201210T040000Z/20201210T080000Z&details=Test&text=Test event",
                calendarUrlGenerator.generate(GOOGLE_CALENDAR, testEvent).toString());
    }

    @Test
    public void generateGoogleCalendarUrlShouldGenerateGCUrlWhenSummerTime() {
        var ticket = EventDetails.builder()
                .start(ZonedDateTime.of(2020, 7, 10, 5, 0, 0, 0, ZoneId.of("Europe/Warsaw")))
                .end(ZonedDateTime.of(2020, 7, 10, 9, 0, 0, 0, ZoneId.of("Europe/Warsaw")))
                .build();

        assertEquals("https://calendar.google.com/calendar/render?action=TEMPLATE&dates=20200710T030000Z/20200710T070000Z&details=null&text=null",
                calendarUrlGenerator.generate(GOOGLE_CALENDAR, ticket).toString());
    }

    @Test
    public void generateGoogleCalendarUrlShouldGenerateGoogleCalendarUrl() {
        assertEquals("https://calendar.google.com/calendar/render?action=TEMPLATE&dates=20191123T080000Z/20191123T100000Z&details=Test&text=Test event",
                calendarUrlGenerator.generate(GOOGLE_CALENDAR, testEvent).toString());
    }

    @Test
    public void generateOutlookCalendarUrlShouldGenerateOutLookCalendarUrl() {
        assertEquals("https://outlook.live.com/calendar/0/deeplink/compose?allday=false&body=Test&enddt=2019-11-23T10:00:00Z&path=%2Fcalendar%2Faction%2Fcompose&rru=addevent&startdt=2019-11-23T08:00:00Z&subject=Test event",
                calendarUrlGenerator.generate(MICROSOFT_OUTLOOK, testEvent).toString());
    }

    @Test
    public void generateOfficeCalendarUrlShouldWork() {
        assertEquals("https://outlook.office.com/calendar/0/deeplink/compose?allday=false&body=Test&enddt=2019-11-23T10:00:00Z&path=%2Fcalendar%2Faction%2Fcompose&rru=addevent&startdt=2019-11-23T08:00:00Z&subject=Test event",
                calendarUrlGenerator.generate(MICROSOFT_OFFICE, testEvent).toString());
    }

    @Test
    public void generateYahooCalendarUrlShouldWork() {
        assertEquals("https://calendar.yahoo.com/?desc=Test&dur=&et=20191123T100000Z&in_loc=&st=20191123T080000Z&title=Test event&v=60",
                calendarUrlGenerator.generate(YAHOO, testEvent).toString());
    }

    @Test
    public void googleCalendarShouldWorkForDatesInTheFutureInSummer() {
        assertEquals("https://calendar.google.com/calendar/render?action=TEMPLATE&dates=20250612T050000Z/20250612T110000Z&details=Test&text=Test event",
                calendarUrlGenerator.generate(GOOGLE_CALENDAR, getForDates(
                        ZonedDateTime.of(2025, 6, 12, 7, 0, 0, 0, ZoneId.of("Europe/Warsaw")),
                        ZonedDateTime.of(2025, 6, 12, 13, 0, 0, 0, ZoneId.of("Europe/Warsaw"))
                )).toString());
    }

    @Test
    public void shouldGenerateInBulk() {
        var result = calendarUrlGenerator.generate(testEvent);
        assertEquals("https://calendar.yahoo.com/?desc=Test&dur=&et=20191123T100000Z&in_loc=&st=20191123T080000Z&title=Test event&v=60", result.yahoo().toString());
        assertEquals("https://outlook.office.com/calendar/0/deeplink/compose?allday=false&body=Test&enddt=2019-11-23T10:00:00Z&path=%2Fcalendar%2Faction%2Fcompose&rru=addevent&startdt=2019-11-23T08:00:00Z&subject=Test event", result.office().toString());
        assertEquals("https://outlook.live.com/calendar/0/deeplink/compose?allday=false&body=Test&enddt=2019-11-23T10:00:00Z&path=%2Fcalendar%2Faction%2Fcompose&rru=addevent&startdt=2019-11-23T08:00:00Z&subject=Test event", result.outlook().toString());
        assertEquals("https://calendar.google.com/calendar/render?action=TEMPLATE&dates=20191123T080000Z/20191123T100000Z&details=Test&text=Test event", result.google().toString());
    }


    private EventDetails getForDates(ZonedDateTime start, ZonedDateTime end) {
        return EventDetails.builder()
                .title("Test event")
                .description("Test")
                .start(start)
                .end(end)
                .build();

    }

}
