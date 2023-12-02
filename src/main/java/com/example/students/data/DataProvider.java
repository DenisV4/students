package com.example.students.data;

import com.example.students.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@ConditionalOnBean(DataInitializer.class)
public class DataProvider {

    public List<Student> getStudents(int limit) {
        Random random = new Random();
        return IntStream.range(1, limit + 1)
                .mapToObj(num -> Student
                        .builder()
                        .firstName("FirstName" + num)
                        .lastName("LastName" + num)
                        .age(random.nextInt(70 - 10) + 10)
                        .build())
                .toList();
    }
}
