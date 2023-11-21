package utn.frc.backend.alquileres.application.responses;

import lombok.*;
import utn.frc.backend.alquileres.models.Tarifa;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarifaSResponse {
    private Long id;
    private Integer tipoTarifa;
    private String definicion;
    private Integer diaSemana;
    private Float montoFijoAlquiler;
    private Float montoMinutoFraccion;
    private Float montoHora;
    private Float montoKm;

    public static TarifaSResponse from(Tarifa tarifa) {
        return TarifaSResponse.builder()
                .id(tarifa.getId())
                .tipoTarifa(tarifa.getTipoTarifa())
                .definicion(tarifa.getDefinicion())
                .diaSemana(tarifa.getDiaSemana())
                .montoFijoAlquiler(tarifa.getMontoFijoAlquiler())
                .montoMinutoFraccion(tarifa.getMontoMinutoFraccion())
                .montoHora(tarifa.getMontoHora())
                .montoKm(tarifa.getMontoKm())
                .build();
    }
}
