package lobodanicolae.U5_W6_D4_SpringWeb.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timestamp) {
}