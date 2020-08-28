/**
 * Ahmed Hamdy
 */
package cs544.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cs544.project.domain.User;
import cs544.project.service.request.UserRequest;
import cs544.project.service.response.Response;
import cs544.project.service.response.UserResponse;

/**
 * @author Ahmed Hamdy
 * @author Kiros Gebregewergs
 *
 */
public interface UserService {

    public List<Response> getAll();

    public Response create(UserRequest user);

    public UserResponse get(long id);

    public String signin(String username, String password);

    public void delete(String username);

    public User searchByUsername(String username);

    public User whoami(HttpServletRequest req);

    public String refresh(String username);

}
