package utn.frc.backend.alquileres.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utn.frc.backend.alquileres.models.Tarifa;
import utn.frc.backend.alquileres.repositories.TarifaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarifaServiceImpl implements TarifaService{
    private  final TarifaRepository tarifaRepository;
    @Override
    public Optional<Tarifa> findTarifaFecha(String definicion, int diaMes, int mes, int anio) {
        return tarifaRepository.findByDefinicionAndDiaMesAndMesAndAnio(definicion, diaMes, mes, anio);
    }

    @Override
    public Optional<Tarifa> findTarifaDiaSemana(int diaSemana) {
        return tarifaRepository.findByDiaSemana(diaSemana);
    }
}
