package utn.frc.backend.alquileres.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import utn.frc.backend.alquileres.models.Alquiler;

import java.util.List;
import java.util.Optional;

public interface AlquilerService {
    List<Alquiler> findAll();
    Optional<Alquiler> findById(Long id);
    List<Alquiler> findAllByIdCliente(Long idCliente);
    List<Alquiler> findAllByMontoGreaterThan(Float monto);
    Alquiler iniciar(Long idCliente, Long idEstRetiro);
    Alquiler finalizar(Alquiler alquiler, Long idEstDevolucion) throws JsonProcessingException;
}
