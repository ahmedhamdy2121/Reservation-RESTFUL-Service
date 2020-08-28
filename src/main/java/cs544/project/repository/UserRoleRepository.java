/**
 * 
 */
/**
 * @author Mohamed Sallam
 *
 */

package cs544.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.project.domain.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByRole(String role);

}
