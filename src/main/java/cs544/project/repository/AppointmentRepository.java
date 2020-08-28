package cs544.project.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cs544.project.domain.Appointment;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface AppointmentRepository
        extends BaseRepository<Appointment, Long> {

}
