package utn.frc.backend.alquileres.services;

import utn.frc.backend.alquileres.models.Tarifa;

import java.util.Optional;

public interface TarifaService {
    Optional<Tarifa> findTarifaFecha(String definicion, int diaMes, int mes, int anio);

    Optional<Tarifa> findTarifaDiaSemana(int diaSemana);
}
