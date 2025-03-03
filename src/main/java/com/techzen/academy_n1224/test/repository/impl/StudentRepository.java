package com.techzen.academy_n1224.test.repository.impl;

import com.techzen.academy_n1224.employees.en.ApiException;
import com.techzen.academy_n1224.employees.en.ErrorCode;
import com.techzen.academy_n1224.test.model.Student;
import com.techzen.academy_n1224.test.repository.IStudentRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {


    public List<Student> findByName(String name) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        List<Student> students = null;
        try {
            students = session.createQuery("FROM Student WHERE name like concat('%', :name,'%') ")
                    .setParameter("name" ,name)
                    .getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return students;
    }

    public Student findById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Student student = null;
        try {
            student = (Student) session.createQuery("FROM Student WHERE id =:id ")
                    .setParameter("id" ,id)
                    .uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return student;
    }

    public Student create(Student student) {
        try(Session session = ConnectionUtil.sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                session.saveOrUpdate(student);
                transaction.commit();
            }catch (Exception e){
                if(transaction!=null){
                    transaction.rollback();
                }
                throw new RuntimeException(e);
            }
        }
        return student;
    }

    public Student delete(int id) {
        Student deletedStudent = findById(id);
        if (deletedStudent != null) {
            try {
                PreparedStatement ps = BaseRepository.getConnection().prepareStatement("delete from student where id = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return deletedStudent;
        }
        return null;
    }

}
