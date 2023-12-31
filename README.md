# jCalendarUrls 📅🔗

Welcome to jCalendarUrls, a Java library that makes it easy to generate URLs for adding events to various calendar providers

## Overview

jCalendarUrls simplifies the process of creating calendar event links compatible with popular providers such as Google Calendar, Outlook, and more. 
Whether you're building a Java application or integrating with existing projects, this library provides a seamless solution for managing calendar events.

## Features

- 🚀 Generate URLs for adding events across multiple calendar platforms.
- 🔄 Easily integrate with Java applications.
- 🌐 Support for popular calendar providers - Google Calendar, Outlook (Live, Office), and Yahoo.
- 🧰 Simple and intuitive API.
- 🛠️ Easy to extend

## Getting Started

### Example Usage

```java
public class CalendarApp {

    public static void main(String[] args) {
        CalendarUrlGenerator calendarUrlGenerator = new CalendarUrlGenerator();

        var event = EventDetails.builder()
                .title("Test event")
                .description("Test")
                .start(ZonedDateTime.of(2019, 11, 23, 9, 0, 0, 0, ZoneId.of("Europe/Warsaw")))
                .end(ZonedDateTime.of(2019, 11, 23, 11, 0, 0, 0, ZoneId.of("Europe/Warsaw")))
                .build();

        // Generate a Google Calendar event link
        URL googleCalendarUrl = calendarUrlGenerator.generate(GOOGLE_CALENDAR, event);
        System.out.println("Google Calendar URL: " + googleCalendarUrl);

        // Generate calendar urls in bulk
        CalendarUrls result = calendarUrlGenerator.generate(event);
        System.out.println("Result: " + result);
    }
}
```

## Contributing

We welcome contributions! If you have suggestions, bug reports, or want to contribute code, simply open a PR

## License

This project is licensed under the MIT License.
