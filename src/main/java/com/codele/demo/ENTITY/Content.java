package com.codele.demo.ENTITY;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private CTYPE type;

    // Many-to-One relationship with Courses
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "courseid") // Foreign key for Courses
    private Courses course;
}
