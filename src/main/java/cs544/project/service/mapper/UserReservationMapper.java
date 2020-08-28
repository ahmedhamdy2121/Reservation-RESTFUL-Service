/**
 * @author Leilena Tekle
 *
 */
package cs544.project.service.mapper;

import org.springframework.stereotype.Component;

import cs544.project.domain.Reservation;
import cs544.project.service.request.ReservationRequest;
import cs544.project.service.response.ReservationResponse;

@Component
public class UserReservationMapper extends
        BaseMapper<Reservation, ReservationRequest, ReservationResponse> {

    public UserReservationMapper() {
        super(Reservation.class, ReservationRequest.class,
              ReservationResponse.class);
    }

}
