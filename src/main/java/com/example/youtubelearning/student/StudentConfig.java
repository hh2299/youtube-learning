package com.example.youtubelearning.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * @ClassName: StudentConfig
 * @Description:
 * @Author: HYJ
 * @Date: 2021-02-24
 * @Version: 1.0
 **/
@Configuration
public class StudentConfig {
  @Bean
  CommandLineRunner commandLineRunner(StudentRepository repository){
    return args -> {
     Student john =  new Student(
              1L,
              "John",
              LocalDate.of(1999, Month.DECEMBER, 5),
              "1111@163.com"
      );
      Student aLex=  new Student(
              2L,
              "Alex",
              LocalDate.of(2000, Month.AUGUST, 24),
              "112@163.com"
      );
      repository.saveAll(List.of(john, aLex));
    };
  }
}
