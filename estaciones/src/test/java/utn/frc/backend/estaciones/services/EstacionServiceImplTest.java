package utn.frc.backend.estaciones.services;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import utn.frc.backend.estaciones.models.Estacion;
import utn.frc.backend.estaciones.repositories.EstacionRepository;
import utn.frc.backend.estaciones.repositories.IdentifierRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EstacionServiceImplTest {
    @InjectMocks
    EstacionServiceImpl estacionService;
    @Mock
    EstacionRepository estacionRepository;
    @Mock
    IdentifierRepository identifierRepository;

    @Test
    public void testFindAll() {
        // given
        Estacion estacion = new Estacion();
        List<Estacion> estaciones = Arrays.asList(estacion);
        when(estacionRepository.findAll()).thenReturn(estaciones);

        // when
        List<Estacion> found = estacionService.findAll();

        // then
        assertEquals(1, found.size());
        assertEquals(estacion, found.get(0));
    }

    @Test
    public void testFindById() {
        // given
        Long id = 1L;
        Estacion estacion = new Estacion();
        estacion.setId(id);
        when(estacionRepository.findById(id)).thenReturn(Optional.of(estacion));

        // when
        Optional<Estacion> found = estacionService.findById(id);

        // then
        assertEquals(Optional.of(estacion), found);
    }

    @Test
    public void testEncontrarEstacionMasCercana() {
        // given
        double latitud = 40.7128;
        double longitud = -74.0060;
        Estacion estacion1 = new Estacion();
        estacion1.setLatitud(40.7128);
        estacion1.setLongitud(-74.0060);
        Estacion estacion2 = new Estacion();
        estacion2.setLatitud(34.0522);
        estacion2.setLongitud(-118.2437);
        List<Estacion> estaciones = Arrays.asList(estacion1, estacion2);
        when(estacionService.findAll()).thenReturn(estaciones);

        // when
        Estacion found = estacionService.encontrarEstacionMasCercana(latitud, longitud);

        // then
        assertEquals(estacion1, found);
    }

    @Test
    public void testCrear() {
        // given
        String nombre = "Estacion Test";
        LocalDateTime fechaHoraCreacion = LocalDateTime.now();
        double latitud = 40.7128;
        double longitud = -74.0060;
        long id = 1L;
        when(identifierRepository.nextValue(Estacion.TABLE_NAME)).thenReturn(Math.toIntExact(id));
        Estacion nuevaEstacion = new Estacion(id, nombre, fechaHoraCreacion, latitud, longitud);
        when(estacionRepository.save(argThat(e -> e.getNombre().equals(nombre) && e.getFechaHoraCreacion().equals(fechaHoraCreacion) && e.getLatitud() == latitud && e.getLongitud() == longitud))).thenReturn(nuevaEstacion);

        // when
        Estacion created = estacionService.crear(nombre, fechaHoraCreacion, latitud, longitud);

        // then
        assertEquals(nuevaEstacion, created);
    }


}
