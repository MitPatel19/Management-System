package com.userappointment.skylink.service;
import com.userappointment.skylink.models.Role;
import com.userappointment.skylink.models.User;
import com.userappointment.skylink.models.dto.RoleDTO;
import com.userappointment.skylink.models.dto.UserDTO;
import com.userappointment.skylink.repository.RoleRepository;
import com.userappointment.skylink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired

    private UserRepository userRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> updateRole(Long id, Role updatedRole) {
        return roleRepository.findById(id).map(role -> {
            role.setRoleName(updatedRole.getRoleName());
            return roleRepository.save(role);
        });
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }


    public List<RoleDTO> getAllRolesWithUsers() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOs = new ArrayList<>();

        for (Role role : roles) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(role.getId());
            roleDTO.setRoleName(role.getRoleName());

            List<User> users = userRepository.findByRole(role);

            List<UserDTO> userDTOs = new ArrayList<>();
            for (User user : users) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getId());
                userDTO.setFirstName(user.getFirstName());
                userDTO.setLastName(user.getLastName());
                userDTOs.add(userDTO);
            }

            roleDTO.setUsers(userDTOs);
            roleDTOs.add(roleDTO);
        }

        return roleDTOs;
    }
}
