package utn.frc.backend.estaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.frc.backend.estaciones.models.Estacion;

public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}
