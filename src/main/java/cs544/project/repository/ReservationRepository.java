/**
 * @author Leilena Tekle
 *
 */
package cs544.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cs544.project.domain.Reservation;

@Repository
public interface ReservationRepository
        extends JpaRepository<Reservation, Long> {

    @Query(value = "FROM Reservation where student_id = :userId")
    List<Reservation> findAllReservationsForUser(long userId);

}
