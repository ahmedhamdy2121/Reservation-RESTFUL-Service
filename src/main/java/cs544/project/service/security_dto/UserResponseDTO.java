/**
 * @author Kiros Gebregewergs
 *
 */
package cs544.project.service.security_dto;

import java.util.Set;

import cs544.project.domain.UserRole;
import io.swagger.annotations.ApiModelProperty;

public class UserResponseDTO {

    @ApiModelProperty(position = 0)
    private Integer id;
    @ApiModelProperty(position = 1)
    private String userName;
    @ApiModelProperty(position = 2)
    private String email;
    @ApiModelProperty(position = 3)
    Set<UserRole> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

}
