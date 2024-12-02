package com.backend.redis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
public class Timer {

    @GetMapping("/time")
    public ResponseEntity<String> getElapsedHours(@RequestParam String time) {
        try {
            // Parse the provided time in HH:mm format
            LocalTime providedTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime currentTime = LocalTime.now();

            // Calculate elapsed time
            java.time.Duration duration = java.time.Duration.between(providedTime, currentTime);

            long elapsedHours = Math.abs(duration.toHours());
            long elapsedMinutes = Math.abs(duration.toMinutesPart());

            String response = String.format("Provided time: %s, Current time: %s, Elapsed time: %d hours and %d minutes",
                    providedTime, currentTime, elapsedHours, elapsedMinutes);
            return ResponseEntity.ok(response);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid time format. Expected HH:mm.");
        }
    }
}
