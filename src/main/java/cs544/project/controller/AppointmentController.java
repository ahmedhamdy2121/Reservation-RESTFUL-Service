package cs544.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cs544.project.domain.Appointment;
import cs544.project.service.AppointmentService;
import cs544.project.service.BaseService;
import cs544.project.service.request.AppointmentRequest;
import cs544.project.service.response.AppointmentReponse;
import io.swagger.annotations.ApiOperation;

@RestController()
public class AppointmentController extends BaseController {

    @Autowired
    protected BaseService<Appointment, AppointmentRequest, AppointmentReponse, Long> baseServie;

    @GetMapping("/appointments/search")
    @ApiOperation(value = "${AppointmentController.get}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_CHECKER')")
    public Page<AppointmentReponse> search(@RequestParam(
            value = "query") String query, Pageable pageable) {
        return baseServie.search(query, pageable);
    }

    @GetMapping("/appointments/{appointmentId}")
    @ApiOperation(value = "${AppointmentController.get}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_CHECKER')")
    public AppointmentReponse getUserAppintmentById(@PathVariable long appointmentId)
            throws Exception {
        return baseServie.findById(appointmentId);
    }

    @PostMapping("/appointments")
    @ApiOperation(value = "${AppointmentController.post}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('CHECKER')")
    public void add(@Valid AppointmentRequest appointmentDto,
                    @RequestParam long checkerId)
            throws Exception {
        ((AppointmentService) baseServie).create(appointmentDto, checkerId);
    }

    @PutMapping("/appointments/{appointmentId}")
    @ApiOperation(value = "${AppointmentController.put}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
    public void update(AppointmentRequest appointmentDto,
                       @PathVariable long appointmentId)
            throws Exception {
        ((AppointmentService) baseServie).update(appointmentDto,
                                                 appointmentId);
    }

    @DeleteMapping("/appointments/{appointmentId}")
    @ApiOperation(value = "${AppointmentController.delete}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CHECKER')")
    public void deleteAppintmentById(@PathVariable long appointmentId)
            throws Exception {
        baseServie.delete(appointmentId);
    }

}
