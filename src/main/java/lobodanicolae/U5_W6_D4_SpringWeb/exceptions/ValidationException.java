package lobodanicolae.U5_W6_D4_SpringWeb.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private List<String> errorMessages;

    public ValidationException(List<String> errorMessages) {
        super("Errori  di validazione!");
        this.errorMessages = errorMessages;
    }
}
