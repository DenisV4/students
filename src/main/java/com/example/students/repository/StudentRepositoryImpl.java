package com.example.students.repository;

import com.example.students.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StudentRepositoryImpl implements StudentRepository {
    private Map<Integer, Student> storage;
    private int counter;

    @PostConstruct
    public void init() {
        this.storage = new HashMap<>();
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Student save(Student student) {
        student.setId(++counter);
        storage.put(student.getId(), student);
        return student;
    }

    public void saveAll(List<Student> students) {
        students.forEach(this::save);
    }

    @Override
    public Optional<Student> deleteById(int id) {
        if (storage.containsKey(id)) {
            return Optional.of(storage.remove(id));
        }
        return Optional.empty();
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }

    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }
}
