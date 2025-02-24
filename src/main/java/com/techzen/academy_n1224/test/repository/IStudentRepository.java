package com.techzen.academy_n1224.test.repository;

import com.techzen.academy_n1224.test.model.Student;

import java.util.List;

public interface IStudentRepository {
    public List<Student> findByName(String name);

    public Student findById( int id);

    public Student create(Student student);


    public Student delete(int id);
}
