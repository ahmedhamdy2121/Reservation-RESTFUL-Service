/**
 * @author Leilena Tekle
 *
 */
package cs544.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cs544.project.config.domain.ReservationStatus;
import cs544.project.domain.Reservation;
import cs544.project.service.BaseService;
import cs544.project.service.UserReservationService;
import cs544.project.service.request.ReservationRequest;
import cs544.project.service.response.ReservationResponse;
import io.swagger.annotations.ApiOperation;

@RestController
public class UserReservationController extends BaseController {

    @Autowired
    protected BaseService<Reservation, ReservationRequest, ReservationResponse, Long> baseServie;

    // get reservations based on user/student id
    @GetMapping("/users/{studentId}/reservations")
    @ApiOperation(value = "${UserReservationController.get}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    public List<ReservationResponse> getReservationById(@PathVariable long studentId)
            throws Exception {
        return ((UserReservationService) baseServie).getByUserId(studentId);
    }

    // get reservation based on date
    @GetMapping("/users/{studentId}/reservations/date")
    @ApiOperation(value = "${UserReservationController.get}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    public List<ReservationResponse> getReservationByDate(@PathVariable long studentId,
                                                          @RequestParam("date") @DateTimeFormat(
                                                                  iso = DateTimeFormat.ISO.DATE) LocalDate date)
            throws Exception {
        return ((UserReservationService) baseServie).getByDate(studentId,
                                                               date);
    }

    // get reservations based on status
    @GetMapping("/users/{studentId}/reservations/state/{state}")
    @ApiOperation(value = "${UserReservationController.get}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    public List<ReservationResponse> getReservationByState(@PathVariable long studentId,
                                                           @PathVariable ReservationStatus state)
            throws Exception {
        return ((UserReservationService) baseServie).getByState(studentId,
                                                                state);
    }

    // flush all reservations
    @GetMapping("/reservations")
    public List<ReservationResponse> getReservations() throws Exception {
        return baseServie.findAll();
    }

    // ACCEPT AND REJECT
    @PutMapping(
            value = "/appointments/{appointmentId}/reservations/{reservationId}",
            params = "state=accept")
    @ApiOperation(value = "${UserReservationController.put}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CHECKER')")
    public String acceptReservation(@PathVariable long appointmentId,
                                    @PathVariable long reservationId)
            throws Exception {
        ((UserReservationService) baseServie).acceptReservation(appointmentId,
                                                                reservationId);
        return "Successfully send message";

    }

    @PutMapping(
            value = "/appointments/{appointmentId}/reservations/{reservationId}",
            params = "state=reject")
    @ApiOperation(value = "${UserReservationController.put}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CHECKER')")
    public String declineReservation(@PathVariable long appointmentId,
                                     @PathVariable long reservationId)
            throws Exception {
        ((UserReservationService) baseServie)
                .declineReservation(appointmentId);
        return "Successfully send message";
    }

}
