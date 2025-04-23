package com.codele.demo.SERVICE.IMPL;

import com.codele.demo.DTO.AddCourseRequest;
import com.codele.demo.DTO.ContentRequest;
import com.codele.demo.DTO.EnrollRequest;
import com.codele.demo.ENTITY.Content;
import com.codele.demo.ENTITY.Courses;
import com.codele.demo.ENTITY.Department;
import com.codele.demo.ENTITY.User;
import com.codele.demo.REPOSITORY.ContentRepository;
import com.codele.demo.REPOSITORY.CoursesRepository;
import com.codele.demo.REPOSITORY.UserRepository;
import com.codele.demo.SERVICE.Coursesservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Coursesimpl implements Coursesservice {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addCourse(AddCourseRequest addCourseDTO) {
        User lecturer = userRepository.findById(addCourseDTO.getLecturerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lecturer not found"));

        Courses course = new Courses();
        course.setTitle(addCourseDTO.getTitle());

        try {
            course.setCategory(Department.valueOf(addCourseDTO.getCategory()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category");
        }

        course.setLecturer(lecturer);

        if (addCourseDTO.getContent() != null && !addCourseDTO.getContent().isEmpty()) {
            List<Content> courseContent = addCourseDTO.getContent().stream().map(contentDTO -> {
                Content content = new Content();
                content.setTitle(contentDTO.getTitle());
                content.setContent(contentDTO.getContent());
                content.setType(contentDTO.getType());
                content.setCourse(course);
                return content;
            }).collect(Collectors.toList());

            course.setContent(courseContent);
        }

        coursesRepository.save(course);
    }

    @Override
    public List<Courses> getCourses() {
        return coursesRepository.findAll();
    }

    @Override
    public Courses getCourse(Integer id) {
        return coursesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    // ✅ Updated to support partial search
    @Override
    public List<Courses> getCourseByname(String title) {
        List<Courses> courses = coursesRepository.findByTitleIgnoreCaseContaining(title.trim());
        if (courses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No courses found for search term: " + title);
        }
        return courses;
    }

    @Override
    public void deleteCourse(int id) {
        Courses course = coursesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid course ID"));
        coursesRepository.delete(course);
    }

    @Override
    public List<Courses> getCoursesByUserId(Integer userId) {
        return coursesRepository.findByUser_Id(userId);
    }

    @Override
    public List<Courses> getCoursesByLecturerId(Integer lecturerId) {
        return coursesRepository.findByLecturer_Id(lecturerId);
    }

    @Override
    public void enrollincourses(EnrollRequest request) {
        Integer userId = request.getUserid();
        Integer courseId = request.getCourseid();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        if (!user.getCourses().contains(course)) {
            user.getCourses().add(course);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is already enrolled in this course");
        }
    }

    @Override
    public void addContentToCourse(Integer courseId, ContentRequest contentRequest) {
        Courses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        Content content = new Content();
        content.setTitle(contentRequest.getTitle());
        content.setType(contentRequest.getType());
        content.setContent(contentRequest.getContent());
        content.setCourse(course);

        contentRepository.save(content);
    }

    @Override
    public void deleteContent(Integer contentId) {
        if (!contentRepository.existsById(contentId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        contentRepository.deleteById(contentId);
    }

    @Override
    public List<Content> getContentsByCourseId(Integer courseId) {
        return contentRepository.findByCourseId(courseId);
    }

    @Override
    public Optional<Content> getContentsByCourseTitle(String title) {
        return contentRepository.findByTitleContainingIgnoreCase(title);
    }
}
