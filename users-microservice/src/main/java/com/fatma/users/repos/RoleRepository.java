package com.fatma.users.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fatma.users.entities.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {


	 @Query("select r from Role r where r.role = ?1")
	    Role findByRole(String role);

	    List<Role> findAll();

	    @Query("select r from Role r where r.role_id = ?1")
	    Role findRoleById(Long id);


}