/**
 * @author mohamedsallam
 *
 */

package cs544.project.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs544.project.domain.Reservation;
import cs544.project.service.request.ReservationRequest;
import cs544.project.service.response.ReservationResponse;

@Component
public class ReservationMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ReservationResponse domainToResponse(Reservation reservation) {
        return modelMapper.map(reservation, ReservationResponse.class);
    }

    public Reservation requestToDomain(ReservationRequest reservationRequest) {
        return modelMapper.map(reservationRequest, Reservation.class);
    }

}
