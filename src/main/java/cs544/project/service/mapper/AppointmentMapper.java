
package cs544.project.service.mapper;

import org.springframework.stereotype.Component;

import cs544.project.domain.Appointment;
import cs544.project.service.request.AppointmentRequest;
import cs544.project.service.response.AppointmentReponse;

@Component
public class AppointmentMapper extends
        BaseMapper<Appointment, AppointmentRequest, AppointmentReponse> {

    public AppointmentMapper() {
        super(Appointment.class, AppointmentRequest.class,
              AppointmentReponse.class);
    }

}
