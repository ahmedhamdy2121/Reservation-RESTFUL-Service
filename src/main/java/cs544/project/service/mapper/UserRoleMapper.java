/**
 * @author mohamedsallam
 *
 */
package cs544.project.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs544.project.domain.UserRole;
import cs544.project.service.request.UserRoleRequest;
import cs544.project.service.response.UserRoleResponse;

@Component
public class UserRoleMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserRoleResponse domainToResponse(UserRole role) {
        return modelMapper.map(role, UserRoleResponse.class);
    }

    public UserRole requestToDomain(UserRoleRequest roleRequest) {
        return modelMapper.map(roleRequest, UserRole.class);
    }

}
