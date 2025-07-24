package lobodanicolae.U5_W6_D4_SpringWeb.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NewBlogPostPayload {
    @NotBlank(message = "La categoria è obbligatoria")
    private String categoria;
    @NotBlank(message = "Il titolo è obbligatorio")
    private String titolo;
    @NotBlank(message = "Il contenuto è obbligatorio")
    private String contenuto;
    @Min(value = 1, message = "Il tempo di lettura deve essere almeno 1 minuto")
    private int tempoDiLettura;
    private Integer authorId; // opzionale, per relazione con Author

}