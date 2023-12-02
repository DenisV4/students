package com.example.students.cli;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CliInfoEvent extends ApplicationEvent {
    private final String message;

    public CliInfoEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}
