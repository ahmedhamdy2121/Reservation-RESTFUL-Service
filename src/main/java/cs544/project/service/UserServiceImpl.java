package cs544.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cs544.project.config.exception.CustomException;
import cs544.project.config.security.JwtTokenProvider;
import cs544.project.domain.User;
import cs544.project.domain.UserRole;
import cs544.project.repository.UserRepository;
import cs544.project.repository.UserRoleRepository;
import cs544.project.service.mapper.UserMapper;
import cs544.project.service.request.UserRequest;
import cs544.project.service.response.NullResponse;
import cs544.project.service.response.Response;
import cs544.project.service.response.UserResponse;

@Service
public class UserServiceImpl
        extends BaseService<User, UserRequest, UserResponse, Long>
        implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public List<Response> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::domainToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Response create(UserRequest userRequest) {
        Optional<UserRequest> opt = Optional.ofNullable(userRequest);
        if (! opt.isPresent())
            return NullResponse.get();

        List<String> userRoles = userRequest.getRoles();
        User user = userMapper.requestToDomain(userRequest);
        List<UserRole> userRoles2 = new ArrayList<>();
        for (String role : userRoles) {
            UserRole userRole = userRoleRepository.findByRole(role);
            userRoles2.add(userRole);
        }
        user.setRolesFromList(userRoles2);
        return userMapper.domainToResponse(userRepository.save(user));

    }

    @Override
    public UserResponse get(long id) {
        User user = userRepository.getOne(id);
        return userMapper.domainToResponse(user);
    }

    public String signin(String username, String password) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username,
                                                                          password));
            return jwtTokenProvider.createToken(username, userRepository
                    .findByUserName(username).getRoleList());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied",
                                      HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) {
        if (! userRepository.existsByUserName(user.getUserName())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUserName(),
                                                user.getRoleList());
        } else {
            throw new CustomException("Username is already in use",
                                      HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userRepository.deleteByUserName(username);
    }

    public User searchByUsername(String username) {
        System.out.println(username);
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist",
                                      HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByUserName(jwtTokenProvider
                .getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository
                .findByUserName(username).getRoleList());
    }

}
