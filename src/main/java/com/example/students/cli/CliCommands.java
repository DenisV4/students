package com.example.students.cli;

import com.example.students.model.Student;
import com.example.students.repository.StudentRepository;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;

import java.text.MessageFormat;
import java.util.stream.Collectors;

@Command
@RequiredArgsConstructor
public class CliCommands {
    private final ApplicationEventPublisher publisher;
    private final StudentRepository studentRepository;

    @Command(command = "ls", description = "Show list of students")
    @CommandAvailability(provider = "studentListAvailabilityProvider")
    public String getAll() {
        return studentRepository.findAll()
                .stream()
                .map(Student::toString)
                .collect(Collectors.joining("\n"));
    }

    @Command(command = "a", description = "Add student")
    public void create(@Size(min = 3, max = 40)
                         @Option(label = "firstName", longNames = "fn", shortNames = 'f', required = true)
                         String firstName,
                         @Size(min = 3, max = 40)
                         @Option(label = "lastName", longNames = "ln", shortNames = 'l', required = true)
                         String lastName,
                         @Positive
                         @Option(label = "age", longNames = "ag", shortNames = 'a', required = true)
                         Integer age) {

        var newStudent = Student
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .build();

        var savedStudent = studentRepository.save(newStudent);
        publisher.publishEvent(new CliInfoEvent(this,  savedStudent + " created"));
    }

    @Command(command = "rm", description = "Remove student by id")
    @CommandAvailability(provider = "studentListAvailabilityProvider")
    public void removeById(@Positive
                             @Option(label = "id", longNames = "id", shortNames = 'i', required = true)
                             Integer id) {

        var student = studentRepository.deleteById(id);
        var message = student.
                map(value -> MessageFormat.format("Student with id={0} removed", value.getId()))
                .orElse("Not found");

        publisher.publishEvent(new CliInfoEvent(this, message));
    }

    @Command(command = "clr", description = "Remove all students")
    @CommandAvailability(provider = "studentListAvailabilityProvider")
    public void removeAll() {
        studentRepository.deleteAll();
    }
}
