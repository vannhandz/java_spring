package com.techzen.academy_n1224.test.service;

import com.techzen.academy_n1224.test.model.Student;

import java.util.List;

public interface IStudentService {
     List<Student> findByName(String name) ;
     Student findById( int id);
     Student create(Student student) ;
     Student delete(int id) ;
}
