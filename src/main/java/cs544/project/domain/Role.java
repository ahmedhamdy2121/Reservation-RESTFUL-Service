/**
 * @author Kiros Gebregewergs
 *
 */

package cs544.project.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN, ROLE_STUDENT, ROLE_CHECKER;

    @Override
    public String getAuthority() {
        return name();
    }

}
