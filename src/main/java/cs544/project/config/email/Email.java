/**
 * @author Leilena Tekle
 */
package cs544.project.config.email;

public class Email {

    private String subject;

    private String message;

    public Email(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public Email() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}