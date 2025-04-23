package com.codele.demo.REPOSITORY;

import com.codele.demo.ENTITY.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {

    Optional<Courses> findByTitle(String title);

    List<Courses> findByTitleIgnoreCaseContaining(String title); // ✅ Partial match, case-insensitive

    List<Courses> findByLecturer_Id(Integer lecturerId);

    List<Courses> findByUser_Id(Integer userId);
}
