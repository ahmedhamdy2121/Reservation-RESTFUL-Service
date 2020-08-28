/**
 * @author Leilena Tekle
 */
package cs544.project.config.email;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import cs544.project.domain.Reservation;
import cs544.project.repository.ReservationRepository;
import cs544.project.service.AppointmentService;
import cs544.project.service.response.AppointmentReponse;

@Aspect
@Component
public class EmailAspect {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        // ENTER SENDER CREDENTIALS HERE!
        mailSender.setUsername("test.eaproject@gmail.com");
        mailSender.setPassword("eaproject123456");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

    @After("execution(public * cs544.project.controller.UserReservationController.acceptReservation(..))")
    public void sendAcceptedEmail(JoinPoint joinPoint) throws Exception {
        Object[] args = joinPoint.getArgs();
        Long reservationId = (Long) args[1];
        Long appointmentId = (Long) args[0];
        AppointmentReponse appointment =
                appointmentService.findById(appointmentId);
        Reservation reservation = reservationRepository.getOne(reservationId);
        // //send email here
        MimeMessage mail1 = javaMailSender.createMimeMessage();
        // send email to student
        MimeMessageHelper helper1 = new MimeMessageHelper(mail1);
        Email studentEmail = AcceptanceEmail
                .getStudentEmail(reservation.getStudent().getFirstName(),
                                 reservation.getDate(),
                                 appointment.getLocation());
        String studentEmailAddress = reservation.getStudent().getEmail();
        helper1.setTo(studentEmailAddress);
        helper1.setText(studentEmail.getMessage());
        helper1.setSubject(studentEmail.getSubject());
        javaMailSender.send(mail1);

        MimeMessage mail2 = javaMailSender.createMimeMessage();
        // send email to checker
        MimeMessageHelper helper2 = new MimeMessageHelper(mail2);
        Email checkerEmail = AcceptanceEmail
                .getCheckerEmail(reservation.getStudent().getFirstName(),
                                 reservation.getDate(),
                                 appointment.getLocation());
        String checkerEmailAddress =
                reservation.getAppointment().getChecker().getEmail();
        helper2.setTo(checkerEmailAddress);
        helper2.setText(checkerEmail.getMessage());
        helper2.setSubject(checkerEmail.getSubject());
        javaMailSender.send(mail2);
        System.out.println("success");
    }
    // public void sendDeclinedEmail(JoinPoint joinPoint, Reservation
    // reservation, Appointment appointment) throws Exception {

    @After("execution(public * cs544.project.controller.UserReservationController.declineReservation(..))")
    public void sendDeclinedEmail(JoinPoint joinPoint) throws Exception {
        Object[] args = joinPoint.getArgs();
        Long reservationId = (Long) args[1];
        Long appointmentId = (Long) args[0];
        AppointmentReponse appointment =
                appointmentService.findById(appointmentId);
        Reservation reservation = reservationRepository.getOne(reservationId);
        // //send email here
        MimeMessage mail1 = javaMailSender.createMimeMessage();

        // send email to student
        MimeMessageHelper helper1 = new MimeMessageHelper(mail1);
        Email studentEmail = DeclineEmail
                .getStudentEmail(reservation.getStudent().getFirstName(),
                                 reservation.getDate(),
                                 appointment.getLocation());
        String studentEmailAddress = reservation.getStudent().getEmail();
        helper1.setTo(studentEmailAddress);
        helper1.setText(studentEmail.getMessage());
        helper1.setSubject(studentEmail.getSubject());
        javaMailSender.send(mail1);

        // send email to checker
        MimeMessage mail2 = javaMailSender.createMimeMessage();
        MimeMessageHelper helper2 = new MimeMessageHelper(mail2);
        Email checkerEmail = DeclineEmail
                .getCheckerEmail(reservation.getStudent().getFirstName(),
                                 reservation.getDate(),
                                 appointment.getLocation());
        String checkerEmailAddress =
                reservation.getAppointment().getChecker().getEmail();
        helper2.setTo(checkerEmailAddress);
        helper2.setText(checkerEmail.getMessage());
        helper2.setSubject(checkerEmail.getSubject());
        javaMailSender.send(mail2);

        System.out.println("success");
    }

}