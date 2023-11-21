package utn.frc.backend.estaciones.application.requests;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstacionCrearRequest {
    @NotBlank(message = "Nombre es requerido")
    private String nombre;
    @NotNull(message = "Latitud es requerida")
    @Max(value = 90, message = "Latitud tiene un valor invalido")
    @Min(value = -90, message = "Latitud tiene un valor invalido")
    private double latitud;
    @NotNull(message = "Longitud es requerida")
    @Max(value = 180, message = "Longitud tiene un valor invalido")
    @Min(value = -180, message = "Longitud tiene un valor invalido")
    private double longitud;
}
