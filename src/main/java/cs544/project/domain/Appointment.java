/**
 * @author Mohamed Sallam
 *
 */
package cs544.project.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import cs544.project.config.domain.Location;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @NotNull
    @Column(nullable = false)
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Location location;

    @ManyToOne
    @NotNull
    private User checker;

    @OneToMany(orphanRemoval = true, mappedBy = "appointment")
    @Cascade(CascadeType.DELETE)
    @Column(nullable = false)
    private Set<Reservation> reservations;

    public Appointment() {
    }

    public Appointment(LocalDate date, LocalTime time, Location location,
                       User checker) {
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

    public User getChecker() {
        return checker;
    }

    public void setChecker(User checker) {
        this.checker = checker;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setReservation(Reservation reservation) {
        this.reservations.add(reservation);

    }

}
