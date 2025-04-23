package com.codele.demo.ENTITY;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Department category;

    // Many-to-One relationship with User for the lecturer who created the course
    @ManyToOne
    @JoinColumn(name = "lecturer_id") // Foreign key in Courses table
    @JsonBackReference // Back reference to prevent recursion when serializing courses
    private User lecturer;

    // Many-to-Many relationship with User (enrolled students)
    @ManyToMany(mappedBy = "courses")
    @JsonIgnore // The 'mappedBy' attribute refers to the field in the User class
    private List<User> user;

    // One-to-Many relationship with Content
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Content> content;
}
