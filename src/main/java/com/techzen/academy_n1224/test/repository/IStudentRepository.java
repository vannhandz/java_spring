package com.techzen.academy_n1224.test.repository;

import com.techzen.academy_n1224.test.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
//    List<Student> findByNameContainingAndScoreBetween(String name, Double scoreAfter, Double scoreBefore);

    @Query("FROM Student where name like concat('%',:name ,'%') and score>= :scoreAfter and (:scoreBefore is null or score <=:scoreBefore)")
    List<Student> findAttribute(@Param("name") String name,
                                @Param("scoreAfter") Double scoreAfter,
                                @Param("scoreBefore") Double scoreBefore);

    public Student findById( int id);

}
