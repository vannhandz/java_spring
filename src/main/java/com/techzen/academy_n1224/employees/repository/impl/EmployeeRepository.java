package com.techzen.academy_n1224.employees.repository.impl;

import com.techzen.academy_n1224.employees.dto.EmployeeSearchRequest;
import com.techzen.academy_n1224.employees.model.Employee;
import com.techzen.academy_n1224.employees.repository.IEmployeeRepository;
import com.techzen.academy_n1224.test.model.Student;
import com.techzen.academy_n1224.test.repository.impl.ConnectionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public List<?> finAttributes(EmployeeSearchRequest employeeSearchRequest) {

        return employees.stream()
                .filter(e -> (employeeSearchRequest.getName() == null || e.getName().toLowerCase().contains(employeeSearchRequest.getName().toLowerCase())))
                .filter(e -> (employeeSearchRequest.getDobFrom() == null || !e.getBirth().isBefore(employeeSearchRequest.getDobFrom())))
                .filter(e -> (employeeSearchRequest.getDobTo() == null || !e.getBirth().isAfter(employeeSearchRequest.getDobTo())))
                .filter(e -> (employeeSearchRequest.getGender() == null || e.getGender().equals(employeeSearchRequest.getGender())))
                .filter(e -> (employeeSearchRequest.getPhone() == null || e.getPhone().contains(employeeSearchRequest.getPhone())))
//                .filter(e -> (employeeSearchRequest.getDepartmentId() == null || Objects.equals(e.getDepartmentId(), employeeSearchRequest.getDepartmentId())))
                .filter(e -> {

                    if (employeeSearchRequest.getSalaryRange() == null) {
                        return true;
                    }
                    return switch (employeeSearchRequest.getSalaryRange()) {
                        case "5" -> e.getSalary() < 5000000;
                        case "5-10" -> e.getSalary() >= 5000000 && e.getSalary() <= 10000000;
                        case "10-20" -> e.getSalary() >= 10000000 && e.getSalary() <= 20000000;
                        case "20" -> e.getSalary() >= 20000000;
                        default -> true;
                    };
                })
                .collect(Collectors.toList());
    }

    public Employee findById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Employee employee = null;
        try {
            employee = (Employee) session.createQuery("FROM Employee WHERE id =:id ")
                    .setParameter("id" ,id)
                    .uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return employee;
    }

    public Employee save(Employee employee) {
        try(Session session = ConnectionUtil.sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            try{
                session.saveOrUpdate(employee);
                transaction.commit();
            }catch (Exception e){
                if(transaction!=null){
                    transaction.rollback();
                }
                throw new RuntimeException(e);
            }
        }
        return employee;
    }

    public Employee deleteEmployees(int id) {
        Employee employee = findById(id);
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(employee);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
