package com.codele.demo.DTO;

import com.codele.demo.ENTITY.Department;
import com.codele.demo.ENTITY.Role;
import com.codele.demo.ENTITY.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;
    private String secondname;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING) // Store department as a string
    private Department department;

    @Enumerated(EnumType.STRING) // Store role as a string
    private Role role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.secondname = user.getSecondname();
        this.role = user.getRole();
        this.department = user.getDepartment();
    }
}
