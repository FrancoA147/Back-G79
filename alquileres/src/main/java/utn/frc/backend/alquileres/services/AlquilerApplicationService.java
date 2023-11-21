package utn.frc.backend.alquileres.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import utn.frc.backend.alquileres.application.responses.AlquilerResponse;
import utn.frc.backend.alquileres.application.responses.InicioAlquilerResponse;

import java.util.List;

public interface AlquilerApplicationService {

    List<AlquilerResponse> findAllAlquileres();

    List<AlquilerResponse> findByIdCliente(Long idCliente);

    AlquilerResponse finalizar(Long id, Long idEstDevolucion, String moneda) throws JsonProcessingException;

    InicioAlquilerResponse iniciar(long idCliente, long idEstRetiro);
}
