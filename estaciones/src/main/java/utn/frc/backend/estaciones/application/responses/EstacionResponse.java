package utn.frc.backend.estaciones.application.responses;

import lombok.*;
import utn.frc.backend.estaciones.models.Estacion;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstacionResponse {
    Long id;
    String nombre;
    LocalDateTime fechaHoraCreacion;
    double latitud;
    double longitud;

    public static EstacionResponse from(Estacion estacion) {
        return EstacionResponse.builder()
                .id(estacion.getId())
                .nombre(estacion.getNombre())
                .fechaHoraCreacion(estacion.getFechaHoraCreacion())
                .latitud(estacion.getLatitud())
                .longitud(estacion.getLongitud())
                .build();
    }
}
