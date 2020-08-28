/**
 * Ahmed Hamdy
 * @author Kiros Gebregewergs
 */
package cs544.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cs544.project.domain.User;
import cs544.project.service.BaseService;
import cs544.project.service.UserService;
import cs544.project.service.request.UserRequest;
import cs544.project.service.response.Response;
import cs544.project.service.response.UserResponse;
import cs544.project.service.security_dto.UserResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Ahmed Hamdy
 *
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    protected BaseService<User, UserRequest, UserResponse, Long> baseServie;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Response> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    public Response createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    public String login(//
                        @ApiParam("Username") @RequestParam String username, //
                        @ApiParam("Password") @RequestParam String password) {
        return userService.signin(username, password);
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.delete}")
    public String delete(@ApiParam("Username") @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserController.search}",
            response = UserResponseDTO.class)
    public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
        return modelMapper.map(userService.searchByUsername(username),
                               UserResponseDTO.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_CHECKER')")
    @ApiOperation(value = "${UserController.me}",
            response = UserResponseDTO.class)
    public UserResponseDTO whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_CHECKER')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

}
