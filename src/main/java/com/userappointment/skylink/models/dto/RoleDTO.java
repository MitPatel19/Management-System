package com.userappointment.skylink.models.dto;

import lombok.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoleDTO {
    private Long id;
    private String roleName;
    private List<UserDTO> users;

    // Constructors, getters, and setters
}
