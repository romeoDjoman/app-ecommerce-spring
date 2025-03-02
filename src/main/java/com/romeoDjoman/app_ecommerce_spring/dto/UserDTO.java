package com.romeoDjoman.app_ecommerce_spring.dto;

import lombok.Data;
import java.util.Set;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private boolean active;
    private Set<RoleDTO> roles;
}
