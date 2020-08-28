/**
 * @author Mohamed Sallam
 *
 */

package cs544.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.project.domain.UserRole;
import cs544.project.repository.UserRoleRepository;
import cs544.project.service.mapper.UserRoleMapper;
import cs544.project.service.request.UserRoleRequest;
import cs544.project.service.response.UserRoleResponse;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRoleResponse> getAll() {
        return userRoleRepository.findAll().stream()
                .map(userRoleMapper::domainToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserRoleRequest create(UserRoleRequest roleRequest) {
        UserRole role = userRoleRepository.findByRole(roleRequest.getRole());
        if (role != null)
            return roleRequest;

        UserRole userRole = userRoleMapper.requestToDomain(roleRequest);
        userRoleRepository.save(userRole);
        return roleRequest;
    }

    @Override
    public UserRoleResponse delete(String role) {
        UserRole userRole = userRoleRepository.findByRole(role);
        userRoleRepository.delete(userRole);
        return userRoleMapper.domainToResponse(userRole);
    }

}
