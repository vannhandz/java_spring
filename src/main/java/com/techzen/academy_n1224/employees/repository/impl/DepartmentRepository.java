package com.techzen.academy_n1224.employees.repository.impl;

import com.techzen.academy_n1224.employees.model.Department;
import com.techzen.academy_n1224.employees.model.Employee;
import com.techzen.academy_n1224.employees.repository.IDepartmentRepository;
import com.techzen.academy_n1224.test.model.Student;
import com.techzen.academy_n1224.test.repository.impl.ConnectionUtil;
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
import java.util.Optional;

@Repository
public class DepartmentRepository implements IDepartmentRepository {


    public List<?> getAll() {

        Session session = ConnectionUtil.sessionFactory.openSession();
        List<Department> departments = null;
        try {
            departments = (List<Department>) session.createQuery("FROM Department  ")
                    .uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return departments;
    }

    public Department findById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Department department = null;
        try {
            department = (Department) session.createQuery("FROM Department WHERE id =:id ")
                    .setParameter("id" ,id)
                    .uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return department;
    }

    public Department save(Department department) {
        try(Session session = ConnectionUtil.sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                session.saveOrUpdate(department);
                transaction.commit();
            }catch (Exception e){
                if(transaction!=null){
                    transaction.rollback();
                }
                throw new RuntimeException(e);
            }
        }
        return department;
    }

    public Department delete(int id) {
        Department department = findById(id);
        try(Session session = ConnectionUtil.sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                session.saveOrUpdate(department);
                transaction.commit();
            }catch (Exception e){
                if(transaction!=null){
                    transaction.rollback();
                }
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
