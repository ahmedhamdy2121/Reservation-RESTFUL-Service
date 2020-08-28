/**
 * @author Leilena Tekle
 *
 */
package cs544.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Leilena Tekle
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs544.project.config.domain.ReservationStatus;
import cs544.project.domain.Appointment;
import cs544.project.domain.Reservation;
import cs544.project.repository.AppointmentRepository;
import cs544.project.service.request.ReservationRequest;
import cs544.project.service.response.ReservationResponse;

@Primary
@Service
@Transactional
public class UserReservationImpl extends
        BaseService<Reservation, ReservationRequest, ReservationResponse, Long>
        implements UserReservationService {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AppointmentRepository appointmentRepository;

    // Reservations based on the students's Id
    @Override
    public List<ReservationResponse> getByUserId(long id) throws Exception {
        return repository.findAll().stream()
                .filter(r -> r.getStudent().getId() == id)
                .map(mapper::domainToResponse).collect(Collectors.toList());
    }

    // Reservations based on the students's Id and date
    @Override
    public List<ReservationResponse> getByDate(long id, LocalDate date)
            throws Exception {
        return repository.findAll().stream()
                .filter(r -> r.getStudent().getId() == id
                        && r.getDate().equals(date))
                .map(mapper::domainToResponse).collect(Collectors.toList());
    }

    // Reservations based on the students's Id and status
    @Override
    public List<ReservationResponse> getByState(long id,
                                                ReservationStatus status)
            throws Exception {
        return repository.findAll().stream()
                .filter(r -> r.getStudent().getId() == id
                        && r.getReservationStatus() == status)
                .map(mapper::domainToResponse).collect(Collectors.toList());
    }

    // find all Reservasions
    @Override
    public List<ReservationResponse> findAll() {
        return repository.findAll().stream().map(mapper::domainToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void acceptReservation(long appointmentId, long reservationId)
            throws Exception {
        Reservation reservation = repository.getOne(reservationId);
        Appointment appointment = appointmentRepository.getOne(appointmentId);
        appointment.setReservation(reservation);
        reservation.setReservationStatus(ReservationStatus.ACCEPTED);
        repository.save(reservation);
        appointmentRepository.save(appointment);
    }

    @Override
    public void declineReservation(long reservationId) throws Exception {
        Reservation reservation = repository.getOne(reservationId);
        reservation.setReservationStatus(ReservationStatus.DECLINED);
        repository.save(reservation);
    }

}
