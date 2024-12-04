package com.rbac.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbac.entities.Permission;
import com.rbac.entities.Role;
import com.rbac.repository.PermissionRepository;
import com.rbac.repository.roleRepository;

@Service
public class RoleService {
	
	@Autowired
	private roleRepository roleRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	
	 public Role createRole(String name, Set<String> permissionNames) {
	        Role role = new Role();
	        role.setName(name);

	        Set<Permission> permissions = new HashSet<>();
	        for (String permissionName : permissionNames) {
	            Permission permission = permissionRepository.findByName(permissionName)
	                    .orElseThrow(() -> new RuntimeException("Permission not found: " + permissionName));
	            permissions.add(permission);
	        }

	        role.setPermissions(permissions);
	        return roleRepository.save(role);
	    }

	    public Optional<Role> findByName(String name) {
	        return roleRepository.findByName(name);
	    }

	    public List<Role> getAllRoles() {
	        return roleRepository.findAll();
	    }

		public Role getRoleById(Long id) {
			return roleRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));
		}

		public Role updateRole(Long id, Role role) {
	        Role existingRole = getRoleById(id);
	        existingRole.setName(role.getName());
	        return roleRepository.save(existingRole);
	    }

		public void deleteRole(Long id) {
			// TODO Auto-generated method stub
			 roleRepository.deleteById(id);
			
		}

		public Role createRoles(Role role) {
			// TODO Auto-generated method stub
			return roleRepository.save(role);
		}
	

}
