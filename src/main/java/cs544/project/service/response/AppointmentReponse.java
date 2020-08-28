/**
 * Ahmed Hamdy
 */
package cs544.project.service.response;

import java.time.LocalDate;
import java.time.LocalTime;

import cs544.project.config.domain.Location;

/**
 * @author Ahmed Hamdy
 *
 */
public class AppointmentReponse implements Response {

    private long id;
    private LocalDate date;
    private LocalTime time;
    private Location location;
    private UserResponse checker;

    public AppointmentReponse() {
    }

    public AppointmentReponse(long id, LocalDate date, LocalTime time,
                              Location location, UserResponse checker) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.location = location;
        this.checker = checker;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UserResponse getChecker() {
        return checker;
    }

    public void setChecker(UserResponse checker) {
        this.checker = checker;
    }

}
