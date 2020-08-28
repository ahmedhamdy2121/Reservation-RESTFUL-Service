/**
 * @author Kiros Gebregewergs
 *
 */
package cs544.project.service.security_dto;

import java.util.Set;

import cs544.project.domain.UserRole;
import io.swagger.annotations.ApiModelProperty;

public class UserDataDTO {

    @ApiModelProperty(position = 0)
    private String userName;
    @ApiModelProperty(position = 1)
    private String email;
    @ApiModelProperty(position = 2)
    private String password;
    @ApiModelProperty(position = 3)
    Set<UserRole> roles;

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

}
