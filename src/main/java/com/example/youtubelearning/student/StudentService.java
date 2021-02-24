package com.example.youtubelearning.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @ClassName: StudentService
 * @Description: 业务层
 * @Author: HYJ
 * @Date: 2021-02-23
 * @Version: 1.0
 **/
@Component
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  /**
   * 查
   * @return
   */
  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  /**
   * 增
   * @param student
   */
  public void addNewStudent(Student student) {
    Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    if (studentOptional.isPresent()) {//已经存在使用此email的学生
      throw new IllegalStateException("email taken");
    }
    //合法则保存
    studentRepository.save(student);
  }

  /**
   * 删
   * @param studentId
   */
  public void deleteStudent(Long studentId) {
    boolean exists = studentRepository.existsById(studentId);
    if (!exists) {
      throw new IllegalStateException("stduent with id" + studentId + "does mot exists");
    }
    studentRepository.deleteById(studentId);
  }

  @Transactional
  public void updateStduent(Long studentId, String name, String email) {
    Student student = studentRepository.findById(studentId).//检查此id学生是否存在
            orElseThrow(() -> new IllegalStateException("student with id" + studentId + " does not exist"));

    if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {//name非空+非无名氏+非重名
      student.setName(name);
    }

    if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
      Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
      if (studentOptional.isPresent()) {
        throw new IllegalStateException("email taken");
      }
      student.setEmail(email);

    }



  }
}
