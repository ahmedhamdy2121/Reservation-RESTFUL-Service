/**
 * Ahmed Hamdy
 */
package cs544.project.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import cs544.project.domain.User;

/**
 * @author Ahmed Hamdy
 *
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    boolean existsByUserName(String username);

    User findByUserName(String username);

    @Transactional
    void deleteByUserName(String username);

}
