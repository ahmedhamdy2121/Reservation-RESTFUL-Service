/**
 * Ahmed Hamdy
 */
package cs544.project.service.request;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cs544.project.config.domain.Gender;
import cs544.project.config.domain.Role;
import cs544.project.service.validator.ValueListOfEnum;
import cs544.project.service.validator.ValueOfEnum;

/**
 * @author Ahmed Hamdy
 *
 */
public class UserRequest {

    @NotNull
    @NotBlank
    @Size(min = 6, max = 30)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 30)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @ValueOfEnum(enumClass = Gender.class)
    private String gender;

    @NotNull
    @Size(min = 4, max = 20)
    private String userName;

    @NotNull
    @Size(min = 5, max = 15)
    private String password;

    @NotNull
    @ValueListOfEnum(enumClass = Role.class)
    private List<String> roles;

    public UserRequest() {
    }

    public UserRequest(String firstName, String lastName, String email,
                       String gender, String userName, String password,
                       List<String> roles) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
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

    public String getGender() {
        return gender;
    }

    public Gender getGenderAsEnum() {
        return Gender.valueOf(gender);
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public List<Role> getRolesAsEnum() {
        return roles.stream().map(Role::valueOf).collect(Collectors.toList());
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserRequest [firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", gender=" + gender + ", userName="
                + userName + ", password=" + password + ", roles=" + roles
                + "]";
    }

}
