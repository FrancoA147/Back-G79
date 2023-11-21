package utn.frc.backend.alquileres.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity(name = "ALQUILERES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Alquiler {
    @Id
    Long id;
    @Column(name = "ID_CLIENTE")
    Long idCliente;
    int estado;
    @Column(name = "ESTACION_RETIRO")
    Long idEstRetiro;
    @Column(name = "ESTACION_DEVOLUCION")
    Long idEstDevolucion;

    @Column(name = "FECHA_HORA_RETIRO")
    LocalDateTime fechaHoraRetiro;
    @Column(name = "FECHA_HORA_DEVOLUCION")
    LocalDateTime fechaHoraDevolucion;
    Float monto;

    @ManyToOne
    @JoinColumn(name = "ID_TARIFA")
    Tarifa tarifa;

    public Alquiler(Long id, Long idCliente, Long idEstRetiro) {
        this.id = id;
        this.idCliente = idCliente;
        this.estado = 1;
        this.idEstRetiro = idEstRetiro;
        this.fechaHoraRetiro = LocalDateTime.now();
    }

    public boolean esFinalizado() {
        return estado == 2;
    }

    public void finalizar(Long idEstDevolucion) {
        this.idEstDevolucion = idEstDevolucion;
        this.estado = 2;
        this.fechaHoraDevolucion = LocalDateTime.now();
    }
}
