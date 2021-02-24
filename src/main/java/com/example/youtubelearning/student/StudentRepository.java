package com.example.youtubelearning.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @ClassName: StudentRepository
 * @Description:
 * @Author: HYJ
 * @Date: 2021-02-24
 * @Version: 1.0
 **/
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
  @Query("SELECT s FROM Student s WHERE  s.email = ?1")
  Optional<Student> findStudentByEmail(String email);

}
