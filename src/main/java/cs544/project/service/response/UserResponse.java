/**
 * Ahmed Hamdy
 */
package cs544.project.service.response;

import java.util.Set;

import cs544.project.config.domain.Gender;

/**
 * @author Ahmed Hamdy
 *
 */
public class UserResponse implements Response {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String userName;

    public UserResponse() {
    }

    public UserResponse(long id, String firstName, String lastName,
                        String email, Gender gender, String userName,
                        Set<UserRoleResponse> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.userName = userName;
        // this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
