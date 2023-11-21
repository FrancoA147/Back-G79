package utn.frc.backend.alquileres.application.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeResponse {
    String moneda;
    Double importe;
}
