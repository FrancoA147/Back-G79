package utn.frc.backend.alquileres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utn.frc.backend.alquileres.models.Alquiler;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    @Query(value = "SELECT count(*) FROM ALQUILERES;", nativeQuery = true)
    Long getMaxId();

    List<Alquiler> findAllByIdCliente(Long idCliente);
    List<Alquiler> findAllByMontoGreaterThan(Float monto);
}
