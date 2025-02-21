package com.techzen.academy_n1224.repository;

import com.techzen.academy_n1224.employees.en.ApiException;
import com.techzen.academy_n1224.employees.en.ErrorCode;
import com.techzen.academy_n1224.model.Student;

import java.util.ArrayList;
import java.util.List;

public interface IStudentRepository {
    public List<Student> findByName(String name);

    public Student findById( int id);

    public Student create(Student student);


    public Student delete(int id);
}
