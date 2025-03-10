package com.techzen.academy_n1224.test.repository;

import com.techzen.academy_n1224.test.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
//    List<Student> findByNameContainingAndScoreBetween(String name, Double scoreAfter, Double scoreBefore);

//    @Query("FROM Student where name like concat('%',:name ,'%') and (:scoreAfter is null or score>= :scoreAfter) and (:scoreBefore is null or score <=:scoreBefore)")
//    Page<Student> findAttribute(@Param("name") String name,
//                                @Param("scoreAfter") Double scoreAfter,
//                                @Param("scoreBefore") Double scoreBefore,
//                                Pageable pageable);

//    @Query("SELECT COUNT(s) FROM Student s WHERE s.name LIKE concat('%',:name ,'%') " +
//            "AND (:scoreAfter IS NULL OR s.score >= :scoreAfter) " +
//            "AND (:scoreBefore IS NULL OR s.score <= :scoreBefore)")
//    long countByAttribute(@Param("name") String name,
//                          @Param("scoreAfter") Double scoreAfter,
//                          @Param("scoreBefore") Double scoreBefore);

    @Query("SELECT s FROM Student s WHERE s.name LIKE concat('%',:name ,'%') " +
            "AND (:scoreAfter IS NULL OR s.score >= :scoreAfter) " +
            "AND (:scoreBefore IS NULL OR s.score <= :scoreBefore)")
    Page<Student> findAttribute(@Param("name") String name,
                                @Param("scoreAfter") Double scoreAfter,
                                @Param("scoreBefore") Double scoreBefore,
                                Pageable pageable);


    public Student findById( int id);

}
