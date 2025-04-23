package com.codele.demo.DTO;

import com.codele.demo.ENTITY.Content;
import lombok.Data;

import java.util.List;

@Data
public class AddCourseRequest {
    private String title;
    private String category;
    private Integer lecturerId;
    private List<ContentRequest> content;

}
