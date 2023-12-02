package com.fatma.users.restControllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatma.users.entities.Role;
import com.fatma.users.entities.User;
import com.fatma.users.repos.UserRepository;
import com.fatma.users.service.MailService;
import com.fatma.users.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserRestController {

	
	
	@Autowired
	UserRepository userRep;
	@Autowired
	UserService userService;
	
	@Autowired
    MailService mailService;
	
	@RequestMapping(path = "/all",method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userRep.findAll();
	 }
	
	
	@RequestMapping(path = "/{id}",method = RequestMethod.GET)
	public Optional<User> getUserById(@PathVariable("id") Long id) {
	return userRep.findById(id);
	 }
	
	@RequestMapping(path="/getByusername/{username}",method = RequestMethod.GET)
	public User getUserByUsername(@PathVariable("username") String username) {
	return userService.findUserByUsername(username);
	 }
	
	@RequestMapping(path="/add",method=RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
	
//	@RequestMapping(path="/createUser",method = RequestMethod.POST)
//	public User createUser(@RequestBody User u) {
//		
//		//ajouter le user
//			User newU  = userService.saveUser(new User(null,u.getUsername(),u.getPassword(),true,u.getEmail(),null));
//			//ajouter les rôles au user	
//			if (u.getRoles() != null) {
//		        for (Role r : u.getRoles()) {
//		            newU = userService.addRoleToUser(newU.getUsername(), r.getRole());
//		        }
//		    }
//		return newU;
//	}
	
//	@RequestMapping(path="/updateUser", method = RequestMethod.PUT)
//	public User updateUser(@RequestBody User u) {
//
//		//ajouter le user
//		User newU  = userService.updateUser(new User(u.getUser_id(),u.getUsername(),u.getPassword(),true,u.getEmail(),null));
//		//ajouter les rôles au user	
//	     newU.setRoles(null);
//		if (u.getRoles() != null) {
//			
//	        for (Role r : u.getRoles()) {
//	            newU = userService.addRoleToUser(newU.getUsername(), r.getRole());
//	        }
//	    }
//	return newU;	
//	}
	
	@RequestMapping(path="/deleteUser/{id}",method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id") Long id)
	{
		//System.out.println("delete");
		userService.deleteUserById(id);
	}
	
	
//	@RequestMapping(path="/register",method = RequestMethod.POST)
//	public User registerUser(@RequestBody User u) {
//		
//		System.out.println("register");
//		
//		//ajouter le user
//			User newU  = userService.saveUser(new User(null,u.getUsername(),u.getPassword(),true,u.getEmail(),null));
//			
//			//ajouter les rôles au user	
//			//if (u.getRoles() != null) {
//		      //  for (Role r : u.getRoles()) {
//		            newU = userService.addRoleToUser(newU.getUsername(), "USER");
//		            System.out.println(newU.toString());
//		      //  }
//		   // }
//		
//		return newU;
//	}
	
	@RequestMapping(path = "activateUser/{username}/{code}", method = RequestMethod.GET)
    public String activateUser(@PathVariable String username,@PathVariable String code ) {

        System.out.println("user activated: " + code);
        User user =userService.activateUser(username, code);
        if (user!=null)
            return "Your account has been verified ";
        else
           return "Your account has not been verified";

    }
    @RequestMapping(path="addRole/{id}",method=RequestMethod.POST)
    public User addRoleToUser(@PathVariable long id,@RequestBody Role r) {
        return userService.addRoleToUser(id, r);
    }
    @RequestMapping(path="addR",method=RequestMethod.POST)
    public Role saveRol(@RequestBody Role rol) {
        return userService.addRole(rol);
    }
	
	
	
	@RequestMapping(path="/changePassword/{id}/{oldPass}/{newPass}",method = RequestMethod.PUT)
	public User ChangePassword(@PathVariable("oldPass") String oldPass,@PathVariable("newPass") 
	String newPass,@PathVariable("id") Long id) {
		System.out.println("old = "+oldPass + " new = "+newPass);
			return  userService.ChangePassword(oldPass,newPass,id);			
	}
	
	@RequestMapping(path="allRoles",method=RequestMethod.GET)
    public List<Role> getAllRoles() {
        return userService.findAllRoles();
    }
	 @RequestMapping(path="findUserById/{id}",method=RequestMethod.GET)
	    public User findUserById(@PathVariable Long id) {
	        return userService.findUserById(id);
	    }
	    @RequestMapping(path="deleteUserById/{id}",method=RequestMethod.DELETE)
	    public void deleteUserById(@PathVariable long id) {
	         userService.deleteUser(id);
	    }

	    @RequestMapping(path="removeRoleFromUer/{id}",method=RequestMethod.POST)
	    public User removeRole(@PathVariable long id,@RequestBody Role r)
	    {
	        return  userService.removeRoleFromUser(id,r);

	    }
	    @RequestMapping(path="findRoleById/{id}",method=RequestMethod.GET)
	    public Role findRoleById(@PathVariable Long id) {
	        return userService.findRoleById(id);
	    }

   /* @RequestMapping(path="/addRole/{username}/{rolename}",method=RequestMethod.POST)
    public User addRoleToUser(@PathVariable String username ,@PathVariable String rolename ) {
        return userService.addRoleToUser(username, rolename);
    }*/
	
}
/*import java.util.List;

import com.fatma.users.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fatma.users.entities.User;
import com.fatma.users.repos.RoleRepository;
import com.fatma.users.service.UserService;
@RestController

@CrossOrigin(origins = "*")
public class UserRestController {
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRep;
    
    @RequestMapping(path = "all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping(path="add",method=RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    

    @RequestMapping(path="addRole/{id}",method=RequestMethod.POST)
    public User addRoleToUser(@PathVariable long id,@RequestBody Role r) {
        return userService.addRoleToUser(id, r);
    }
    @RequestMapping(path="addR",method=RequestMethod.POST)
    public Role saveRol(@RequestBody Role rol) {
        return userService.addRole(rol);
    }
    //je veut passer l id in the url
    @RequestMapping(path="findUserById/{id}",method=RequestMethod.GET)
    public User findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @RequestMapping(path="allRoles",method=RequestMethod.GET)
    public List<Role> getAllRoles() {
        return userService.findAllRoles();
    }

    @RequestMapping(path="findRoleById/{id}",method=RequestMethod.GET)
    public Role findRoleById(@PathVariable Long id) {
        return userService.findRoleById(id);
    }

    @RequestMapping(path="deleteUserById/{id}",method=RequestMethod.DELETE)
    public void deleteUserById(@PathVariable long id) {
         userService.deleteUser(id);
    }

    @RequestMapping(path="removeRoleFromUer/{id}",method=RequestMethod.POST)
    public User removeRole(@PathVariable long id,@RequestBody Role r)
    {
        return  userService.removeRoleFromUser(id,r);

    }


}*/
