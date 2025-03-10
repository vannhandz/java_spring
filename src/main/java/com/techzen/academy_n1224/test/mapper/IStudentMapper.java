package com.techzen.academy_n1224.test.mapper;

import com.techzen.academy_n1224.test.dto.student.StudentRequest;
import com.techzen.academy_n1224.test.dto.student.StudentResponse;
import com.techzen.academy_n1224.test.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStudentMapper {
    Student studentResquestToStudent(StudentRequest studentRequest);
    StudentResponse studentToStudentResponse(Student student);
}
