/**
 * @author Mohamed Sallam
 *
 */
package cs544.project.service.request;

public class UserRoleRequest {

    private String role;

    public UserRoleRequest() {
    }

    public UserRoleRequest(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
