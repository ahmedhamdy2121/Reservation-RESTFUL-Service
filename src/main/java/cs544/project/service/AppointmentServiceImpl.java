package cs544.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs544.project.config.domain.Location;
import cs544.project.config.exception.ResourceNotFoundException;
import cs544.project.domain.Appointment;
import cs544.project.domain.User;
import cs544.project.repository.UserRepository;
import cs544.project.service.request.AppointmentRequest;
import cs544.project.service.response.AppointmentReponse;

@Service
@Transactional
public class AppointmentServiceImpl extends
        BaseService<Appointment, AppointmentRequest, AppointmentReponse, Long>
        implements AppointmentService {

    @Autowired
    private UserRepository userRepository;

    public void create(AppointmentRequest appointmentRequest, long checkerId)
            throws ResourceNotFoundException {

        User checker = userRepository.getOne(checkerId);
        if (checker == null)
            throw new ResourceNotFoundException("checker not found");
        Appointment domain = mapper.requestToDomain(appointmentRequest);
        domain.setChecker(checker);
        repository.save(domain);

    }

    @Override
    public List<AppointmentReponse> getByUserId(long id)
            throws ResourceNotFoundException {
        User checker = userRepository.getOne(id);
        if (checker == null)
            throw new ResourceNotFoundException("checker not found");

        return repository.findAll().stream()
                .filter(a -> a.getChecker().getId() == id)
                .map(mapper::domainToResponse).collect(Collectors.toList());
    }

    @Override
    public AppointmentReponse findById(long id)
            throws ResourceNotFoundException {
        Appointment appointment = repository.getOne(id);
        return mapper.domainToResponse(appointment);
    }

    @Override
    public List<AppointmentReponse> findAll() {
        return repository.findAll().stream().map(mapper::domainToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void update(AppointmentRequest appointmentRequest, long id) {
        // TODO: convert to mapper
        Appointment entity = repository.getOne(id);
        entity.setLocation(Location.valueOf(appointmentRequest.getLocation()));
        entity.setDate(appointmentRequest.getDate());
        entity.setTime(appointmentRequest.getTime());
        repository.save(entity);

    }

}
