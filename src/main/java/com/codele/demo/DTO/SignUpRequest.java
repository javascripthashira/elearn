package com.codele.demo.DTO;

import com.codele.demo.ENTITY.Department;
import com.codele.demo.ENTITY.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class SignUpRequest {
    private String firstname;
    private String secondname;
    private String Email;
    private String Password;
    private Role role;
    private Department department;
}
