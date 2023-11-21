package utn.frc.backend.alquileres.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "TARIFAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tarifa {
    @Id
    Long id;
    @Column(name = "TIPO_TARIFA")
    Integer tipoTarifa;
    @Column(name = "DEFINICION")
    String definicion;
    @Column(name = "DIA_SEMANA")
    Integer diaSemana;
    @Column(name = "DIA_MES")
    Integer diaMes;
    Integer mes;
    Integer anio;
    @Column(name = "MONTO_FIJO_ALQUILER")
    Float montoFijoAlquiler;
    @Column(name = "MONTO_MINUTO_FRACCION")
    Float montoMinutoFraccion;
    @Column(name = "MONTO_HORA")
    Float montoHora;
    @Column(name = "MONTO_KM")
    Float montoKm;
}
