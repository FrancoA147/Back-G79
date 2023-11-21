package utn.frc.backend.alquileres.application.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.alquileres.application.ResponseHandler;
import utn.frc.backend.alquileres.application.requests.FinalizarAlquilerRequest;
import utn.frc.backend.alquileres.application.requests.IniciarAlquilerRequest;
import utn.frc.backend.alquileres.application.responses.AlquilerResponse;
import utn.frc.backend.alquileres.application.responses.InicioAlquilerResponse;
import utn.frc.backend.alquileres.services.AlquilerApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/alquileres")
@RequiredArgsConstructor
public class AlquilerController {
    private final AlquilerApplicationService alquilerApplicationService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            List<AlquilerResponse> alquileres = alquilerApplicationService.findAllAlquileres();
            return ResponseHandler.success(alquileres);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @GetMapping(params = {"idCliente"})
    public ResponseEntity<Object> getAllByIdCliente(@Valid @Positive @RequestParam Long idCliente){
        try{
            List<AlquilerResponse> alquileres = alquilerApplicationService.findByIdCliente(idCliente);
            return ResponseHandler.success(alquileres);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PatchMapping("/finalizar/{id}")
    public ResponseEntity<Object> finalizar(@PathVariable Long id, @Valid @RequestBody FinalizarAlquilerRequest request) {
        try {
            AlquilerResponse alquiler = alquilerApplicationService.finalizar(id,
                    request.getIdEstacionDevolucion(), request.getMoneda());
            return ResponseHandler.success(alquiler);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }

    @PostMapping
    public ResponseEntity<Object> iniciar(@RequestBody IniciarAlquilerRequest request) {
        try {
            InicioAlquilerResponse alquiler = alquilerApplicationService.iniciar(request.getIdCliente(), request.getIdEstRetiro());
            return ResponseHandler.created(alquiler);
        } catch(IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        } catch (Exception e) {
            return ResponseHandler.internalError();
        }
    }
}
