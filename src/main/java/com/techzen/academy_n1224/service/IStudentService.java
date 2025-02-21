package com.techzen.academy_n1224.service;

import com.techzen.academy_n1224.model.Student;

import java.util.List;

public interface IStudentService {
     List<Student> findByName(String name) ;
     Student findById( int id);
     Student create(Student student) ;
     Student delete(int id) ;
}
