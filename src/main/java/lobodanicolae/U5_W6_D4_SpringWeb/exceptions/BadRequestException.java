package lobodanicolae.U5_W6_D4_SpringWeb.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg) {
        super(msg);
    }
}
