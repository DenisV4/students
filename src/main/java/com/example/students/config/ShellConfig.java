package com.example.students.config;

import com.example.students.cli.CliCommands;
import com.example.students.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.shell.command.annotation.EnableCommand;

@Configuration
@EnableCommand(CliCommands.class)
@RequiredArgsConstructor
public class ShellConfig {
    private final StudentRepository studentRepository;

    @Bean
    public AvailabilityProvider studentListAvailabilityProvider() {
        return () -> !studentRepository.isEmpty()
                ? Availability.available()
                : Availability.unavailable("list of students is empty");
    }
}
