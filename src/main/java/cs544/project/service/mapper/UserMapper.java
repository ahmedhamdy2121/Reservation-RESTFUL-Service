/**
 * Ahmed Hamdy
 */
package cs544.project.service.mapper;

import org.springframework.stereotype.Component;

import cs544.project.domain.User;
import cs544.project.service.request.UserRequest;
import cs544.project.service.response.UserResponse;

/**
 * @author Ahmed Hamdy
 *
 */
@Component
public class UserMapper extends BaseMapper<User, UserRequest, UserResponse> {

    public UserMapper() {
        super(User.class, UserRequest.class, UserResponse.class);
    }

    public User requestToDomain(UserRequest userRequest) {
        modelMapper.typeMap(UserRequest.class, User.class)
                .addMappings(mapper -> {
                    mapper.map(UserRequest::getGenderAsEnum, User::setGender);
                    mapper.map(UserRequest::getRolesAsEnum,
                               User::setRolesFromList);
                });
        return modelMapper.map(userRequest, User.class);
    }

}
