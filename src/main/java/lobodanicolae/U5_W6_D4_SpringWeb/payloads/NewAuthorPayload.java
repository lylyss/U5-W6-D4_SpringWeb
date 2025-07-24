package lobodanicolae.U5_W6_D4_SpringWeb.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NewAuthorPayload {
    @NotBlank(message = "Il nome è obbligatorio")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]{2,40}$", message = "Il nome può contenere solo lettere, spazi, apostrofi e trattini (2-40 caratteri)")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]{2,40}$", message = "Il cognome può contenere solo lettere, spazi, apostrofi e trattini (2-40 caratteri)")
    private String cognome;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Formato email non valido")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Formato email non valido")
    private String email;

    @NotBlank(message = "La data di nascita è obbligatoria")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La data deve essere nel formato aaaa-mm-gg")
    private String dataDiNascita; // "aaaa-mm-gg"
}
