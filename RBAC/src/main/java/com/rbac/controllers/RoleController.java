package com.rbac.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.rbac.entities.Role;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private com.rbac.services.RoleService roleService;

    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public com.rbac.entities.Role createRole(@Valid @RequestBody Role role) {
        return roleService.createRoles(role);
    }

   
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return "Role deleted successfully.";
    }
}

