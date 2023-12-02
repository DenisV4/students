package com.example.students.data;

import com.example.students.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty("app.data.init.enabled")
public class DataInitializer {
    private final DataProvider dataProvider;
    private final StudentRepository studentRepository;

    @Value("${app.data.student-count:100}")
    private int studentCount;

    @EventListener(ContextRefreshedEvent.class)
    public void insertStudents() {
        var students = dataProvider.getStudents(studentCount);
        studentRepository.saveAll(students);
    }
}
