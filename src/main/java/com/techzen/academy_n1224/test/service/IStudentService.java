package com.techzen.academy_n1224.test.service;

import com.techzen.academy_n1224.test.model.Student;

import java.util.List;

public interface IStudentService {
     public List<Student> findByName(String name,Double scoreAfter, Double scoreBefore);
     Student findById( int id);
     Student create(Student student) ;

     void deleteById(int id) ;
}
