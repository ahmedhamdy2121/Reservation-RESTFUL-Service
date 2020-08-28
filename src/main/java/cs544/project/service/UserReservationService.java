/**
 * @author Leilena Tekle
 *
 */
package cs544.project.service;

import java.time.LocalDate;
import java.util.List;

import cs544.project.config.domain.ReservationStatus;
import cs544.project.service.response.ReservationResponse;

public interface UserReservationService {

    List<ReservationResponse> getByState(long id, ReservationStatus status)
            throws Exception;
    List<ReservationResponse> getByDate(long id, LocalDate date)
            throws Exception;
    List<ReservationResponse> getByUserId(long id) throws Exception;

    void acceptReservation(long appointmentId, long reservationId)
            throws Exception;
    void declineReservation(long reservationId) throws Exception;

}
