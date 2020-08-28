/**
 * Ahmed Hamdy
 */
package cs544.project.service.request;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import cs544.project.config.domain.ReservationStatus;
import cs544.project.service.validator.ValueOfEnum;

/**
 * @author Ahmed Hamdy
 *
 */
public class ReservationRequest {

    @NotNull
    @ValueOfEnum(enumClass = ReservationStatus.class)
    private String reservationStatus;

    @NotNull
    @FutureOrPresent
    private LocalDate date;

    @NotNull
    @FutureOrPresent
    private LocalTime time;

    public ReservationRequest() {
    }

    public ReservationRequest(String reservationStatus) {
        this.reservationStatus = reservationStatus;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
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

}
