package com.codele.demo.DTO;

import com.codele.demo.ENTITY.Department;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class EnrollRequest {
    private Integer userid;
    private Integer courseid;


}
