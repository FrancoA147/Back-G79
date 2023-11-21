package utn.frc.backend.alquileres.application.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.*;
import org.springframework.http.ResponseEntity;
import utn.frc.backend.alquileres.models.Alquiler;
import utn.frc.backend.alquileres.services.RestService;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlquilerResponse {
    private static RestService restService;

    Long id;
    Long idCliente;
    int estado;
    String estRetiro;
    String estDevolucion;
    LocalDateTime fechaHoraRetiro;
    LocalDateTime fechaHoraDevolucion;
    String monto;
    Optional<Object> tarifa;

    public static AlquilerResponse from(Alquiler alquiler) {

            return AlquilerResponse.builder()
                    .id(alquiler.getId())
                    .idCliente(alquiler.getIdCliente())
                    .estado(alquiler.getEstado())
                    //.estRetiro(restService.getEstacionResponse(alquiler.getIdEstRetiro()))
                    //.estDevolucion(restService.getEstacionResponse(alquiler.getIdEstDevolucion()))
                    .estRetiro(alquiler.getIdEstRetiro().toString())
                    .estDevolucion(alquiler.getIdEstDevolucion().toString())
                    .fechaHoraRetiro(alquiler.getFechaHoraRetiro())
                    .fechaHoraDevolucion(alquiler.getFechaHoraDevolucion())
                    .monto((alquiler.getMonto() == null)?null:String.format("%.02f", alquiler.getMonto()))
                    .tarifa(alquiler.getTarifa() != null ?
                            Optional.of(Objects.equals(alquiler.getTarifa().getDefinicion(), "S") ?
                                    TarifaSResponse.from(alquiler.getTarifa()) :
                                    TarifaCResponse.from(alquiler.getTarifa())) :
                            Optional.empty())
                    .build();
    }
}

