package com.codele.demo.CONTROLLER;

import com.codele.demo.DTO.AddCourseRequest;
import com.codele.demo.DTO.ContentRequest;
import com.codele.demo.DTO.EnrollRequest;
import com.codele.demo.ENTITY.Content;
import com.codele.demo.ENTITY.Courses;
import com.codele.demo.SERVICE.Coursesservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private Coursesservice coursesservice;

    // Get all courses
    @GetMapping()
    public List<Courses> getCourses() {
        return coursesservice.getCourses();
    }

    // Get course by ID
    @GetMapping("/{id}")
    public Courses getCourse(@PathVariable int id) {
        return coursesservice.getCourse(id);
    }

    // Search course by title
    @GetMapping("/search")
    public List<Courses> getCourseByname(@RequestParam String title) {
        return coursesservice.getCourseByname(title);
    }

    // Get content of a course by course ID
    @GetMapping("/coursecontent/{id}")
    public List<Content> getCoursecontent(@PathVariable("id") Integer courseid) {
        return coursesservice.getContentsByCourseId(courseid);
    }

    // Add a new course (lecturer functionality)
    @PostMapping("/lecturer/addcourse")
    public String addcourse(@RequestBody AddCourseRequest request) {
        coursesservice.addCourse(request);
        return "success";
    }

    // Add content to a course (lecturer functionality)
    @PostMapping("/lecturer/addcourse/addcontent")
    public String addContent(@RequestBody ContentRequest request) {
        coursesservice.addContentToCourse(request.getCourseId(), request);
        return "success";
    }

    // Delete a course by ID (lecturer functionality)
    @DeleteMapping("/lecturer/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        coursesservice.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    // Delete content from a course by content ID (lecturer functionality)
    @DeleteMapping("/lecturer/deletecontent/{id}")
    public ResponseEntity<Void> deleteCoursecontent(@PathVariable Integer id) {
        coursesservice.deleteContent(id);
        return ResponseEntity.noContent().build();
    }

    // Search content by title
    @GetMapping("/searchcontent")
    public Optional<Content> searchcontent(@RequestParam String title) {
        return coursesservice.getContentsByCourseTitle(title);
    }

    // Enroll a user in a course
    @PostMapping("/enroll")
    public String enrollInCourse(@RequestBody EnrollRequest request) {
        coursesservice.enrollincourses(request);
        return "User successfully enrolled in the course";
    }

    // Get courses enrolled by a user
    @GetMapping("/user/{userId}")
    public List<Courses> getCoursesByUserId(@PathVariable Integer userId) {
        return coursesservice.getCoursesByUserId(userId);
    }

    // Get courses created by a lecturer
    @GetMapping("/lecturer/{lecturerId}")
    public List<Courses> getCoursesByLecturerId(@PathVariable Integer lecturerId) {
        return coursesservice.getCoursesByLecturerId(lecturerId);
    }
}
