/**
 * @author Leilena Tekle
 *
 */
package cs544.project.config.email;

import java.time.LocalDate;

import cs544.project.config.domain.Location;

public class DeclineEmail {

    private static Email checkerEmail;

    private static Email studentEmail;

    public static Email getStudentEmail(String studentName, LocalDate date,
                                        Location location) {
        studentEmail = new Email();
        studentEmail.setSubject("TM Checking Reservation");
        studentEmail.setMessage("Dear " + studentName
                + " We are sorry to inform you that your TM checking has not been approved for "
                + date + " at " + location
                + ". Please try a different date or contact us.");
        return studentEmail;
    }

    public static Email getCheckerEmail(String studentName, LocalDate date,
                                        Location location) {
        checkerEmail = new Email();
        checkerEmail.setSubject("TM Checking Reservation");
        checkerEmail
                .setMessage("You have declined the TM checking reservation made by "
                        + studentName + " on " + date + " at " + location
                        + ".");
        return checkerEmail;
    }

}