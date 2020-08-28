/**
 * @author Leilena Tekle
 *
 */
package cs544.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.project.domain.Reservation;
import cs544.project.repository.ReservationRepository;

/**
 * @author Leilena Tekle
 *
 */

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.findAll().forEach(reservations::add);
        return reservations;

    }

    public Reservation getReservation(Long id) {
        return reservationRepository.getOne(id);
    }

    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void updateReservation(Long id, Reservation reservation) {

        reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

}
