/**
 * @author mohamedsallam
 *
 */
package cs544.project.service;

import java.util.List;

import cs544.project.domain.Reservation;

public interface ReservationService {

    List<Reservation> getAllReservations();
    Reservation getReservation(Long id);
    void addReservation(Reservation reservation);
    void updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);

}
