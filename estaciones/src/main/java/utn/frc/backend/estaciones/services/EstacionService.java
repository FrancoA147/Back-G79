package utn.frc.backend.estaciones.services;

import utn.frc.backend.estaciones.models.Estacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EstacionService {
    List<Estacion> findAll();
    Estacion encontrarEstacionMasCercana(double latitud, double longitud);
    Optional<Estacion> findById(Long id);
    Estacion crear(String nombre, LocalDateTime fechaHoraCreacion, double latitud, double longitud);

}
