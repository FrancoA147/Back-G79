package utn.frc.backend.estaciones.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity(name = Estacion.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Estacion {
    public static final String TABLE_NAME = "ESTACIONES";
    @Id
    @NotNull
    Long id;
    @NotEmpty
    String nombre;
    @Column(name = "FECHA_HORA_CREACION")
    LocalDateTime fechaHoraCreacion;
    @NotNull(message = "Longitud es requerida")
    @Max(value = 180, message = "Latitud tiene un valor invalido")
    @Min(value = -180, message = "Latitud tiene un valor invalido")
    double latitud;
    @NotNull(message = "Longitud es requerida")
    @Max(value = 180, message = "Longitud tiene un valor invalido")
    @Min(value = -180, message = "Longitud tiene un valor invalido")
    double longitud;
}
