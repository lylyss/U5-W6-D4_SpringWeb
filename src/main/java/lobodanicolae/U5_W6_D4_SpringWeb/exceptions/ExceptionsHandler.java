package lobodanicolae.U5_W6_D4_SpringWeb.exceptions;

import jakarta.validation.ValidationException;
import lobodanicolae.U5_W6_D4_SpringWeb.payloads.ErrorsDTO;
import lobodanicolae.U5_W6_D4_SpringWeb.payloads.ErrorsWithListDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsWithListDTO handleValidationErrors(ValidationException ex) {
        return new ErrorsWithListDTO(ex.getMessage(), LocalDateTime.now(), List.of(ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsDTO handleBadRequest(BadRequestException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsDTO handleNotFound(NotFoundException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsDTO handleServerError(Exception ex) {
        ex.printStackTrace();
        return new ErrorsDTO("C'è stato un errore generico! Giuro che lo risolveremo presto!", LocalDateTime.now());
    }
}