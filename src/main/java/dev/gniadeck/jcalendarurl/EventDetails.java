package dev.gniadeck.jcalendarurl;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record EventDetails(String title, String description, ZonedDateTime start, ZonedDateTime end) {
}
