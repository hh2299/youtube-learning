package com.example.youtubelearning.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName: StudentController
 * @Description:
 * @Author: HYJ
 * @Date: 2021-02-23
 * @Version: 1.0
 **/
@RestController //控制层注解
@RequestMapping(path = "api/v1/student")
public class StudentController {

  private final StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  /**
   *
   * @return 简单字符
   */
//  @GetMapping("/hello1")
//  public String hello1() {
//    return "hello";//返回浏览器hello
//  }
//
//  @GetMapping("/hello2")
//  public List<String> hello2() {
//    return List.of("Hello", "World");
//  }

  /**
   * 查
   * @return
   */
  @GetMapping
  public List<Student> getStudents() {
    return studentService.getStudents();
  }

  /**
   * 增
   * @param student
   */
  @PostMapping
  public void registerNewStudent(@RequestBody Student student) {
    studentService.addNewStudent(student);
  }

  /**
   * 删
   * @param id
   */
  @DeleteMapping(path = "{studentId}")
  public void deleteStudent(@PathVariable("studentId") Long id) {
    studentService.deleteStudent(id);
  }

  /**
   * 改
   * @param studentId
   * @param name
   * @param email
   */
  @PutMapping(path = "{studentId}")
  public void updateStudent(
          @PathVariable("studentId") Long studentId,
          @RequestParam(required = false) String name,
          @RequestParam(required = false) String email) {
    studentService.updateStduent(studentId, name, email);
  }

}
