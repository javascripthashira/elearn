package com.codele.demo.REPOSITORY;

import com.codele.demo.ENTITY.Content;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content,Integer> {
    List<Content> findByCourseId(Integer courseId);

    Optional<Content> findByTitle(String title);

    Optional<Content> findByTitleContainingIgnoreCase(String title);
}
