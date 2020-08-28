/**
 * @author Mohamed Sallam
 *
 */
package cs544.project.service.response;

public class UserRoleResponse implements Response {

    private long id;
    private String role;

    public UserRoleResponse() {
    }

    public UserRoleResponse(long id, String role) {
        this.id = id;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
