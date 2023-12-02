package com.example.students.repository;

import com.example.students.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    List<Student> findAll();

    Student save(Student student);

    void saveAll(List<Student> students);

    Optional<Student> deleteById(int id);

    void deleteAll();

    boolean isEmpty();
}
