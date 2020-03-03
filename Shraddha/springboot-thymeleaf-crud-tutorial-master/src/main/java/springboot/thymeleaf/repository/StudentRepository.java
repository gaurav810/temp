package springboot.thymeleaf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.thymeleaf.entity.Student;


@Repository
public interface StudentRepository extends  JpaRepository<Student, Long>{
    
    Student findByUsername(String username);
    
}
