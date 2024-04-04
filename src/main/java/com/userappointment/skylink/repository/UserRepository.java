package com.userappointment.skylink.repository;
import com.userappointment.skylink.models.Role;
import com.userappointment.skylink.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Add custom methods if needed

    List<User> findByRole(Role role);

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role.id IN :roleIds")
    List<User> findByRoleIds(@Param("roleIds") List<Long> roleIds);

    List<User> findByLastNameContainsIgnoreCaseOrFirstNameContainsIgnoreCaseOrIdIn(String lastName, String firstName, List<Long> Ids, Pageable pageable);


}
