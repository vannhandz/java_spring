package com.techzen.academy_n1224.employees.repository.impl;

import com.techzen.academy_n1224.employees.model.Department;
import com.techzen.academy_n1224.employees.model.Employee;
import com.techzen.academy_n1224.employees.repository.IDepartmentRepository;
import com.techzen.academy_n1224.test.model.Student;
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
        List<Department> departments = new ArrayList<>();
        try {
            PreparedStatement ps = BaseRepository.getConnection().prepareStatement("SELECT * FROM department");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                departments.add(Department.builder()
                        .id(rs.getInt("id_department"))
                        .name(rs.getString("name")).build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return departments;
    }

    public Department findById(int id) {
        try {
            PreparedStatement ps = BaseRepository.getConnection().prepareStatement("SELECT *FROM department where id_department = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return Department.builder()
                        .id(rs.getInt("id_department"))
                        .name(rs.getString("name"))
                        .build();
            }
            ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Department save(Department department) {
        if (findById(department.getId()) == null) {
            try {
                PreparedStatement ps = BaseRepository.getConnection().prepareStatement("INSERT INTO department (name) values(?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, department.getName());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    department.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                PreparedStatement ps = BaseRepository.getConnection().prepareStatement("update  department set name=? where id_department=?");
                ps.setString(1, department.getName());
                ps.setInt(2, department.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public Department delete(int id) {
        Department department = findById(id);
        if (department != null) {
            try {
                PreparedStatement ps = BaseRepository.getConnection().prepareStatement("delete from department where id_department = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return department;
        }
        return null;
    }
}
