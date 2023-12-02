package com.fatma.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fatma.users.entities.Role;
import com.fatma.users.repos.RoleRepository;

public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository rolerepo;
	@Override
	public Role saveRole(Role r) {
		return rolerepo.save(r);
	}
	
	@Override
	public List<Role> getAllRoles() {
		return rolerepo.findAll();
	}

	@Override
	public Role getRole(Long id) {
		return rolerepo.findById(id).get();
	}

	@Override
	public Role updateRole(Role r) {
		return rolerepo.save(r);
	}

	@Override
	public void deleteRole(Role r) {
		rolerepo.delete(r);
		
	}

	@Override
	public void deleteRoleById(Long id) {
		rolerepo.deleteById(id);
		
	}

}
