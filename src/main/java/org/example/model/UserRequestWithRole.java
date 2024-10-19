package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestWithRole {
    private String username;
    private String password;
    private Role role;
}
