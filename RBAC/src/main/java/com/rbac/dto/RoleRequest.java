package com.rbac.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RoleRequest {

    @NotBlank(message = "Role name is mandatory")
    @Size(min = 3, max = 50, message = "Role name must be between 3 and 50 characters")
    private String roleName;

    @NotBlank(message = "Role description is mandatory")
    @Size(max = 250, message = "Role description must not exceed 250 characters")
    private String roleDescription;

    public RoleRequest() {
    }

    public RoleRequest(String roleName, String roleDescription) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Override
    public String toString() {
        return "RoleRequest{" +
                "roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                '}';
    }
}

