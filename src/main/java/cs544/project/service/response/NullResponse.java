/**
 * Ahmed Hamdy
 */
package cs544.project.service.response;

import java.util.Optional;

/**
 * @author Ahmed Hamdy
 *
 */
public class NullResponse implements Response {

    private String message;

    private static NullResponse instance;

    public static NullResponse get() {
        return Optional.ofNullable(instance)
                .orElseGet(NullResponse::createInstance);
    }

    private static NullResponse createInstance() {
        instance = new NullResponse();
        return instance;
    }

    private NullResponse() {
        this.message = "Error 400: Request is null!";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
