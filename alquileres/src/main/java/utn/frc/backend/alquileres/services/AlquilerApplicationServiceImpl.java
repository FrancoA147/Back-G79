package utn.frc.backend.alquileres.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utn.frc.backend.alquileres.application.responses.AlquilerResponse;
import utn.frc.backend.alquileres.application.responses.InicioAlquilerResponse;
import utn.frc.backend.alquileres.models.Alquiler;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlquilerApplicationServiceImpl implements AlquilerApplicationService{
    private final AlquilerService alquilerService;
    private final ExchangeService exchangeService;
    private final RestService restService;

    @Override
    public List<AlquilerResponse> findAllAlquileres() {
        return alquilerService.findAll()
                .stream()
                .map(AlquilerResponse::from)
                .toList();
    }

    @Override
    public List<AlquilerResponse> findByIdCliente(Long idCliente) {
        return alquilerService.findAllByIdCliente(idCliente)
                .stream()
                .map(AlquilerResponse::from)
                .toList();
    }

    @Override
    public AlquilerResponse finalizar(Long id, Long idEstDevolucion, String moneda) throws JsonProcessingException {
        restService.getEstacionResponse(idEstDevolucion);
        String[] monedas = {"USD","EUR","CLP","BRL","COP","PEN","GBP"};
        if (!(moneda == null || moneda.trim().isEmpty()) && !Arrays.stream(monedas).anyMatch(moneda::equals)) throw new IllegalArgumentException("Conversion no permitida");
        Alquiler alquiler = alquilerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alquiler not found"));
        restService.getEstacionResponse(alquiler.getIdEstRetiro());

        alquilerService.finalizar(alquiler, idEstDevolucion);

        AlquilerResponse alquilerResponse = AlquilerResponse.from(alquiler);

        if (Arrays.asList(monedas).contains(moneda)) {
            double montoExchanged = exchangeService.getMonto(moneda, alquiler.getMonto().doubleValue());
            alquilerResponse.setMonto(String.format("%.02f", montoExchanged));
        }
        return alquilerResponse;
    }

    @Override public InicioAlquilerResponse iniciar(long idCliente, long idEstRetiro) {
        restService.getEstacionResponse(idEstRetiro);

        return InicioAlquilerResponse.from(alquilerService.iniciar(idCliente, idEstRetiro));
    }
}

