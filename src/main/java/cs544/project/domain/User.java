/**
 * @author Mohamed Sallam
 *
 *
 */
package cs544.project.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import cs544.project.config.domain.Gender;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "This field is required")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "This field is required")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "This field is required")
    @Email(message = "Email format is required")
    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "This field is required")
    @Column(unique = true, nullable = false)
    private String userName;

    @NotNull(message = "This field is required")
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> roles = new HashSet<>();

    @Transient
    private List<Role> roleList;

    public User() {
    }

    public User(String firstName, String lastName, String email, Gender gender,
                String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.userName = userName;
        this.password = password;

    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public void setRolesFromList(List<UserRole> roles) {
        this.roles = new HashSet<>();
        this.roles.addAll(roles);
    }

    public long getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.roles.add(role);
    }

    public List<Role> getRoleList() {
        List<Role> roleList = new ArrayList<>();

        for (UserRole userRole : roles) {
            if (userRole.getRole().equals("ADMIN")) {
                roleList.add(Role.ROLE_ADMIN);
            } else if (userRole.getRole().equals("STUDENT")) {
                roleList.add(Role.ROLE_STUDENT);
            } else if (userRole.getRole().equals("CHECKER")) {
                roleList.add(Role.ROLE_CHECKER);
            }
        }
        return roleList;
    }

    public UserRole getRole(UserRole role) {
        return role;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName="
                + lastName + ", email=" + email + ", gender=" + gender
                + ", userName=" + userName + ", password=" + password
                + ", roles=" + roles + "]";
    }

}
