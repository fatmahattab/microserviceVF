package com.fatma.users.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fatma.users.entities.Role;
import com.fatma.users.service.RoleService;


public class RoleRestController {
	@Autowired
	RoleService roleservice;
	
	@RequestMapping(path="all",method=RequestMethod.GET)
	public List<Role> getAllRoles(){
		
		return roleservice.getAllRoles();
	}
	@RequestMapping(value="/getbyid/{id}",method=RequestMethod.GET)
	public Role getRoleById(@PathVariable("id") Long id){
		
		return roleservice.getRole(id);
	}
	
	
	@RequestMapping(path="/addrol",method=RequestMethod.POST)
	public Role createRole(@RequestBody Role role){
		
		return roleservice.saveRole(role);
	}
	@RequestMapping(path="/updaterol",method=RequestMethod.PUT)
	public Role updateRole(@RequestBody Role role) {
		return roleservice.updateRole(role);
		}
	
	@RequestMapping(value="/delrole/{id}",method=RequestMethod.DELETE)
	public void deleteRole(@PathVariable("id") Long id){
		
		 roleservice.deleteRoleById(id);
	}
	
	
	
}
