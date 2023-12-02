package com.fatma.users.service;

import java.util.List;

import com.fatma.users.entities.Role;
import com.fatma.users.entities.User;

public interface UserService {
	User saveUser(User user);
	User findUserByUsername (String username);
	Role addRole(Role role);
   // User addRoleToUser(Long id, Role r);
	List<User> findAllUsers();
	
	User updateUser(User user);
	void deleteUserById(Long id);
	User getUserById(Long id);

	User ChangePassword(String oldPass ,String newPass, Long id);
    List<Role> findAllRoles();
    
    Role findRoleById(Long id);
    void deleteRoleById(long id);

 
    User activateUser(String username , String code );

  
    User addRoleToUser(long id, Role r);

    User findUserById(Long id);
    void deleteUser(long id);
    User removeRoleFromUser(long id, Role r);
}
