package utn.frc.backend.estaciones.application.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.estaciones.application.ResponseHandler;
import utn.frc.backend.estaciones.application.requests.EstacionCrearRequest;
import utn.frc.backend.estaciones.application.responses.EstacionResponse;
import utn.frc.backend.estaciones.services.EstacionService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/estaciones")
@RequiredArgsConstructor
public class EstacionController {
    private final EstacionService estacionService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            val estaciones = estacionService.findAll()
                    .stream()
                    .map(EstacionResponse::from)
                    .toList();
            return ResponseHandler.success(estaciones);
        }
        catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }
        catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            return estacionService.findById(id)
                    .map(EstacionResponse::from)
                    .map(ResponseHandler::success)
                    .orElseGet(ResponseHandler::notFound);
        }
        catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }
        catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping({"/cercana"})
    public ResponseEntity<Object> encontrarEstacionMasCercana(@Valid @RequestParam("latitud") double latitud,
                                                              @Valid @RequestParam("longitud") double longitud) {
        try {
            return ResponseHandler.success(EstacionResponse.from(estacionService.encontrarEstacionMasCercana(latitud, longitud)));
        }
        catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }
        catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> crear(@Valid @RequestBody EstacionCrearRequest estRequest) {

        try {
            val estacion = estacionService.crear(
                    estRequest.getNombre(),
                    LocalDateTime.now(),
                    estRequest.getLatitud(),
                    estRequest.getLongitud());
            return ResponseHandler.created(EstacionResponse.from(estacion));
        }
        catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }
        catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
