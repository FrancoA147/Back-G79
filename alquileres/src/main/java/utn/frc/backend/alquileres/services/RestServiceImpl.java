package utn.frc.backend.alquileres.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RestServiceImpl implements RestService{

    private final RestTemplate restTemplate;

    public RestServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Long getLatitud(Long idEstacion) throws JsonProcessingException {
        String url = "http://localhost:8084/api/estaciones/".concat(idEstacion.toString());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String responseBody = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseBody);
        JsonNode latitud = root.path("latitud");
        return latitud.asLong();
    }

    @Override
    public Long getLongitud(Long idEstacion) throws JsonProcessingException {
        String url = "http://localhost:8084/api/estaciones/".concat(idEstacion.toString());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String responseBody = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseBody);
        JsonNode longitud = root.path("longitud");
        return longitud.asLong();
    }

    @Override
    public ResponseEntity getEstacionResponse(Long idEstacion) {
        String url = "http://localhost:8084/api/estaciones/" + idEstacion;
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        return responseEntity;
    }
}
