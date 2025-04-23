package com.codele.demo.SERVICE;

import com.codele.demo.DTO.AddCourseRequest;
import com.codele.demo.DTO.ContentRequest;
import com.codele.demo.DTO.EnrollRequest;
import com.codele.demo.ENTITY.Content;
import com.codele.demo.ENTITY.Courses;
import com.codele.demo.ENTITY.Department;

import java.util.List;
import java.util.Optional;

public interface Coursesservice {






    void addCourse(AddCourseRequest addCourseDTO);

    List<Courses> getCourses();

    Courses getCourse(Integer id);

    List<Courses> getCourseByname(String title);

    void deleteCourse(int id);


    List<Courses> getCoursesByUserId(Integer userId);

    List<Courses> getCoursesByLecturerId(Integer lecturerId);

    void enrollincourses(EnrollRequest request);

    void addContentToCourse(Integer courseId, ContentRequest contentRequest);

    void deleteContent(Integer contentId);

    List<Content> getContentsByCourseId(Integer courseId);

    Optional<Content> getContentsByCourseTitle(String title);
}
