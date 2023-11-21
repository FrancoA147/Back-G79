package utn.frc.backend.alquileres.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;

public interface RestService {

    public Long getLatitud(Long idEstacion) throws JsonProcessingException;

    public Long getLongitud(Long idEstacion) throws JsonProcessingException;

    public ResponseEntity<Object> getEstacionResponse(Long idEstacion);
}
