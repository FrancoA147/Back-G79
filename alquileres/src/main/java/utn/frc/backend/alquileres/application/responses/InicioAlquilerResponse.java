package utn.frc.backend.alquileres.application.responses;

import lombok.*;
import utn.frc.backend.alquileres.models.Alquiler;
import utn.frc.backend.alquileres.services.RestService;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InicioAlquilerResponse {

    //private static RestService restService;
    private Long id;
    private Long idCliente;
    private int estado;
    private String estacionRetiro;
    private LocalDateTime fechaHoraRetiro;

    public static InicioAlquilerResponse from (Alquiler alquiler) {
        return InicioAlquilerResponse.builder()
                .id(alquiler.getId())
                .idCliente(alquiler.getIdCliente())
                .estado(alquiler.getEstado())
                //.estacionRetiro(restService.getEstacionResponse(alquiler.getIdEstRetiro()))
                .estacionRetiro(alquiler.getIdEstRetiro().toString())
                .fechaHoraRetiro(alquiler.getFechaHoraRetiro())
                .build();
    }
}
