package utn.frc.backend.alquileres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.backend.alquileres.models.Tarifa;

import java.util.Optional;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
    Optional<Tarifa> findByDefinicionAndDiaMesAndMesAndAnio(String definicion, int diaMes, int mes, int anio);

    Optional<Tarifa> findByDiaSemana(int diaSemana);
}
