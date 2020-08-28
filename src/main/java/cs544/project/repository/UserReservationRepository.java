/**
 * @author Leilena Tekle
 *
 */
package cs544.project.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cs544.project.domain.Reservation;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserReservationRepository
        extends BaseRepository<Reservation, Long> {
}
