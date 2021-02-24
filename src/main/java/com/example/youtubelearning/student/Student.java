package com.example.youtubelearning.student;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

/**
 * @ClassName: Student
 * @Description: 学生实体类
 * @Author: HYJ
 * @Date: 2021-02-23
 * @Version: 1.0
 **/
@Entity
@Table
@NoArgsConstructor
public class Student {

  @Id
  @SequenceGenerator(
          name = "student_sequence",
          sequenceName = "student_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "student_sequence"
  )
  private Long id;
  private String name;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone = "GMT+8")
  private LocalDate dob = LocalDate.now();//默认今日
  private String email;
  @Transient
  private Integer age;


  public Student(String name, LocalDate dob, String email) {
    this.name = name;
    this.dob = dob;
    this.email = email;
  }

  public Student(Long id, String name, LocalDate dob, String email) {
    this.id = id;
    this.name = name;
    this.dob = dob;
    this.email = email;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /**
   *  年龄随时间推移而增加,不应该是固定的从数据库中取出
   * @return
   */
  public Integer getAge() {
    return Period.between(this.dob, LocalDate.now()).getYears();
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Student{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", dob=" + dob +
            ", email='" + email + '\'' +
            ", age=" + age +
            '}';
  }
}
