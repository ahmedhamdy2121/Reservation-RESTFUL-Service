/**
 * Ahmed Hamdy
 */
package cs544.project.service.request;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import cs544.project.config.domain.Location;
import cs544.project.service.validator.ValueOfEnum;

/**
 * @author Ahmed Hamdy
 *
 */
public class AppointmentRequest {

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    @NotNull
    @ValueOfEnum(enumClass = Location.class)
    private String location;

    public AppointmentRequest() {
    }

    public AppointmentRequest(LocalDate date, LocalTime time,
                              String location) {
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
