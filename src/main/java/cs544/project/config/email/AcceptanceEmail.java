/**
 * @author Leilena Tekle
 */
package cs544.project.config.email;

import java.time.LocalDate;

import cs544.project.config.domain.Location;

public class AcceptanceEmail {

    private static Email checkerEmail;

    private static Email studentEmail;

    public static Email getStudentEmail(String studentName, LocalDate date,
                                        Location location) {
        studentEmail = new Email();
        studentEmail.setSubject("TM Checking Reservation");
        studentEmail.setMessage("Dear " + studentName
                + " Your TM checking has been approved for " + date + " at "
                + location + ". See you there!");
        return studentEmail;
    }

    public static Email getCheckerEmail(String studentName, LocalDate date,
                                        Location location) {
        checkerEmail = new Email();
        checkerEmail.setSubject("TM Checking Reservation");
        checkerEmail
                .setMessage("You have approved the TM checking reservation made by "
                        + studentName + " on " + date + " at " + location
                        + ". See you there!");
        return checkerEmail;
    }

}