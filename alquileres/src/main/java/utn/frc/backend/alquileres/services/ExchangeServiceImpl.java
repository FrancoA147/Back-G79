package utn.frc.backend.alquileres.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import utn.frc.backend.alquileres.application.requests.ExchangeRequest;
import utn.frc.backend.alquileres.application.responses.ExchangeResponse;

import java.net.http.HttpHeaders;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {
    @Override public double getMonto(String monedaDestino, Double importe){

        try {
            RestTemplate template = new RestTemplate();

            HttpEntity<ExchangeRequest> entity = new HttpEntity<>(new ExchangeRequest(monedaDestino, importe));

            ResponseEntity<ExchangeResponse> res = template.postForEntity(
                    "http://34.82.105.125:8080/convertir", entity, ExchangeResponse.class
            );

            if (res.getStatusCode().is2xxSuccessful()) {
                return Objects.requireNonNull(res.getBody()).getImporte();
            } else {
                return 0;
            }
        } catch (HttpClientErrorException ex) {
            throw new HttpClientErrorException(ex.getStatusCode());
        }
    }
}
