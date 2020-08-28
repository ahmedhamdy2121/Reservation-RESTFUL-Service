/**
 * Ahmed Hamdy
 */
package cs544.project.service.response;

import java.time.LocalDate;
import java.time.LocalTime;

import cs544.project.config.domain.ReservationStatus;

/**
 * @author Ahmed Hamdy
 *
 */
public class ReservationResponse implements Response {

    private long id;
    private ReservationStatus reservationStatus;
    private LocalDate date;
    private LocalTime time;
    private UserResponse student;
    private AppointmentReponse appointment;

    public ReservationResponse() {
    }

    public ReservationResponse(long id, ReservationStatus reservationStatus,
                               UserResponse student,
                               AppointmentReponse appointment) {

        this.id = id;
        this.reservationStatus = reservationStatus;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.student = student;
        this.appointment = appointment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = LocalDate.now();
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = LocalTime.now();
    }

    public UserResponse getStudent() {
        return student;
    }

    public void setStudent(UserResponse student) {
        this.student = student;
    }

    public AppointmentReponse getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentReponse appointment) {
        this.appointment = appointment;
    }

}
