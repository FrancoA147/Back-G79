package utn.frc.backend.alquileres.application.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IniciarAlquilerRequest {
    @NotNull
    long idCliente;
    @NotNull
    long idEstRetiro;
}
