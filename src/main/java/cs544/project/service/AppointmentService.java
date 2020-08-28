package cs544.project.service;

import java.util.List;

import cs544.project.config.exception.ResourceNotFoundException;
import cs544.project.service.request.AppointmentRequest;
import cs544.project.service.response.AppointmentReponse;

public interface AppointmentService {

    public void create(AppointmentRequest appointmentDto, long id)
            throws ResourceNotFoundException;

    public List<AppointmentReponse> findAll();

    public void update(AppointmentRequest appointmentDto, long id);

    public AppointmentReponse findById(long id)
            throws ResourceNotFoundException;

    public List<AppointmentReponse> getByUserId(long id)
            throws ResourceNotFoundException;

}
