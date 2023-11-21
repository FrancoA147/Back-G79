package utn.frc.backend.alquileres.application.responses;

import lombok.*;
import utn.frc.backend.alquileres.models.Tarifa;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarifaCResponse {
    private Long id;
    private Integer tipoTarifa;
    private String definicion;
    private Integer diaMes;
    private Integer mes;
    private Integer anio;
    private Float montoFijoAlquiler;
    private Float montoMinutoFraccion;
    private Float montoHora;
    private Float montoKm;

    public static TarifaCResponse from(Tarifa tarifa) {
        return TarifaCResponse.builder()
                .id(tarifa.getId())
                .tipoTarifa(tarifa.getTipoTarifa())
                .definicion(tarifa.getDefinicion())
                .diaMes(tarifa.getDiaMes())
                .mes(tarifa.getMes())
                .anio(tarifa.getAnio())
                .montoFijoAlquiler(tarifa.getMontoFijoAlquiler())
                .montoMinutoFraccion(tarifa.getMontoMinutoFraccion())
                .montoHora(tarifa.getMontoHora())
                .montoKm(tarifa.getMontoKm())
                .build();
    }
}
