/**
 * @author Mohamed Sallam
 *
 */
package cs544.project.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import cs544.project.config.domain.ReservationStatus;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private ReservationStatus reservationStatus;

    @NotNull
    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate date;

    @NotNull
    @Column(nullable = false)
    private LocalTime time;

    @ManyToOne
    @NotNull
    private User student;

    @ManyToOne
    @NotNull
    private Appointment appointment;

    public Reservation() {
    }

    public Reservation(User student, Appointment appointment) {
        this.reservationStatus = ReservationStatus.PENDING;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.student = student;
        this.appointment = appointment;
    }

    public long getId() {
        return id;
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

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

}
