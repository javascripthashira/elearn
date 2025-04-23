package com.codele.demo.DTO;

import com.codele.demo.ENTITY.CTYPE;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class ContentRequest {

        private Integer courseId;
        private String title;
        private CTYPE type;
        private String content;




}
