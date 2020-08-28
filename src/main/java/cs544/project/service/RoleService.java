/**
 * @author Mohamed Sallam
 *
 */
package cs544.project.service;

import java.util.List;

import cs544.project.service.request.UserRoleRequest;
import cs544.project.service.response.UserRoleResponse;

public interface RoleService {

    List<UserRoleResponse> getAll();
    UserRoleRequest create(UserRoleRequest roleRequest);
    UserRoleResponse delete(String role);

}
