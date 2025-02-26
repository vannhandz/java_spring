package com.techzen.academy_n1224.test.repository.impl;

import com.techzen.academy_n1224.employees.en.ApiException;
import com.techzen.academy_n1224.employees.en.ErrorCode;
import com.techzen.academy_n1224.test.model.Student;
import com.techzen.academy_n1224.test.repository.IStudentRepository;
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

    private final List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "nhan", 7.0),
                    new Student(2, "tuan", 7.0),
                    new Student(3, "tu", 7.0)
            )
    );

    public List<Student> findByName(String name) {
        List<Student> stSearch = new ArrayList<>();
        try {
            PreparedStatement ps = BaseRepository.getConnection().prepareStatement("SELECT id,name,score FROM student where name like concat('%',?,'%') ");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                stSearch.add(Student.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .score(rs.getDouble("score")).build());
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stSearch;
    }

    public Student findById(int id) {
        try {
            PreparedStatement ps = BaseRepository.getConnection().prepareStatement("SELECT id,name,score FROM student where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return Student.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .score(rs.getDouble("score")).build();
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Student create(Student student) {
        if(findById(student.getId()) == null) {
            try {
                PreparedStatement ps = BaseRepository.getConnection().prepareStatement("insert into student(name ,score ) values (?,?)" , Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, student.getName());
                ps.setDouble(2, student.getScore());

                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    student.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                PreparedStatement ps = BaseRepository.getConnection().prepareStatement("update student set name = ? , score=? where id=?");
                ps.setString(1, student.getName());
                ps.setDouble(2, student.getScore());
                ps.setInt(3, student.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
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
