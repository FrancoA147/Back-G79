package utn.frc.backend.estaciones.services;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import utn.frc.backend.estaciones.models.Estacion;
import utn.frc.backend.estaciones.repositories.EstacionRepository;
import utn.frc.backend.estaciones.repositories.IdentifierRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstacionServiceImpl implements EstacionService{

    private final EstacionRepository estacionRepository;
    private final IdentifierRepository identifierRepository;

    @Override
    public List<Estacion> findAll() {
        return estacionRepository.findAll();
    }

    private double distancia(double lat1, double lat2, double lon1, double lon2) {
        double lat = lat1 - lat2;
        double lon = lon1 - lon2;
        return Math.sqrt(lat*lat + lon*lon);
    }
    @Override
    public Estacion encontrarEstacionMasCercana(double latitud, double longitud) {
        List<Estacion> estaciones = findAll();
        if (latitud > 90 | latitud < -90) {
            throw new IllegalArgumentException("La latitud no es valida");
        }
        if (longitud > 180 | longitud < -180) {
            throw new IllegalArgumentException("La longitud no es valida");
        }

        return estaciones.stream().min(Comparator.comparingDouble(x -> distancia(latitud, x.getLatitud(), longitud, x.getLongitud())))
                .orElse(null);
    }

    @Override
    public Optional<Estacion> findById(Long id) {
        return estacionRepository.findById(id);
    }

    @Override
    public Estacion crear(String nombre, LocalDateTime fechaHoraCreacion, double latitud, double longitud) {
        val id = identifierRepository.nextValue(Estacion.TABLE_NAME);
        val nuevaEstacion = new Estacion((long)id, nombre, fechaHoraCreacion, latitud, longitud);
        return estacionRepository.save(nuevaEstacion);
    }
}
