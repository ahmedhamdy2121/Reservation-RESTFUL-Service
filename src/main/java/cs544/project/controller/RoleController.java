/**
 * 
 */
/**
 * @author Mohamed Sallam
 *
 */

package cs544.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cs544.project.service.RoleService;
import cs544.project.service.request.UserRoleRequest;
import cs544.project.service.response.UserRoleResponse;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<UserRoleResponse> getAllRoles() {
        return roleService.getAll();
    }

    @PostMapping(value = "/create")
    public UserRoleRequest createRole(@Valid @RequestBody UserRoleRequest userRoleRequest) {
        return roleService.create(userRoleRequest);
    }

    @DeleteMapping(value = "/delete")
    public UserRoleResponse deleteRole(@RequestParam("role") String role) {
        return roleService.delete(role);
    }

}
