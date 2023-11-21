package utn.frc.backend.alquileres.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utn.frc.backend.alquileres.models.Alquiler;
import utn.frc.backend.alquileres.models.Tarifa;
import utn.frc.backend.alquileres.repositories.AlquilerRepository;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlquilerServiceImpl implements AlquilerService{
    private final AlquilerRepository alquilerRepository;
    private final TarifaService tarifaService;
    private final RestService restService;

    @Override
    public List<Alquiler> findAll() {
        return alquilerRepository.findAll();
    }

    @Override
    public Optional<Alquiler> findById(Long id) {
        return alquilerRepository.findById(id);
    }

    @Override
    public List<Alquiler> findAllByIdCliente(Long idCliente) {
        return alquilerRepository.findAllByIdCliente(idCliente);
    }

    @Override
    public List<Alquiler> findAllByMontoGreaterThan(Float monto) {
        return alquilerRepository.findAllByMontoGreaterThan(monto);
    }

    @Override
    public Alquiler iniciar(Long idCliente, Long idEstRetiro) {
        Alquiler alq = new Alquiler(alquilerRepository.getMaxId()+1,idCliente, idEstRetiro);
        alquilerRepository.save(alq);
        return alq;
    }

    @Override
    public Alquiler finalizar(Alquiler alquiler, Long idEstDevolucion) throws JsonProcessingException {
        if (alquiler.esFinalizado()) { throw new IllegalArgumentException("Alquiler ya estaba finalizado");}

        alquiler.finalizar(idEstDevolucion);
        Tarifa tarifa = seleccionTarifa(alquiler);
        alquiler.setTarifa(tarifa);
        alquiler.setMonto(calcularMonto(alquiler, tarifa));
        alquilerRepository.save(alquiler);
        return alquiler;
    }

    private Tarifa seleccionTarifa(Alquiler alquiler) {
        int diaMes = alquiler.getFechaHoraRetiro().getDayOfMonth();
        int mes = alquiler.getFechaHoraRetiro().getMonthValue();
        int anio = alquiler.getFechaHoraRetiro().getYear();
        Optional<Tarifa> tarifa = tarifaService.findTarifaFecha("C", diaMes, mes, anio);

        if(tarifa.isEmpty()) {
            int diaSemana = alquiler.getFechaHoraDevolucion().getDayOfWeek().getValue();
            tarifa = tarifaService.findTarifaDiaSemana(diaSemana);
        }

        if(tarifa.isEmpty()) { throw new IllegalArgumentException("Tarifa no encontrada"); }
        return tarifa.get();
    }

    private float calcularMonto(Alquiler alquiler, Tarifa tarifa) throws JsonProcessingException {
        double monto = 0;
        monto += tarifa.getMontoFijoAlquiler();
        Duration duracion = Duration.between(alquiler.getFechaHoraRetiro(), alquiler.getFechaHoraDevolucion());
        long horas;
        long minutos;
        try {
            horas = duracion.toHours();
            minutos = duracion.toMinutes() - horas*60;
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(("Las fechas son demasiado distantes"));
        }

        if (minutos <= 31) {
            monto += tarifa.getMontoMinutoFraccion()*minutos;
        }
        else { horas++; }

        monto += tarifa.getMontoHora()*horas;
        Long retiroLat = restService.getLatitud(alquiler.getIdEstRetiro());
        Long retiroLon = restService.getLatitud(alquiler.getIdEstRetiro());
        Long devolucionLat = restService.getLatitud(alquiler.getIdEstDevolucion());
        Long devolucionLon = restService.getLatitud(alquiler.getIdEstDevolucion());
        double distancia = calcularDistanciaEnKM(retiroLat, devolucionLat, retiroLon, devolucionLon);

        monto += tarifa.getMontoKm()*distancia;
        return (float)monto;
    }

    private double calcularDistanciaEnKM(double lat1, double lat2, double lon1, double lon2) {
        double lat = lat1 - lat2;
        double lon = lon1 - lon2;
        return Math.sqrt(lat * lat + lon * lon) * 110;
    }
}
