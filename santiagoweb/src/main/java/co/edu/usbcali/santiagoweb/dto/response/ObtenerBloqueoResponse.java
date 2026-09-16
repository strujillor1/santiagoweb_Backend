package co.edu.usbcali.santiagoweb.dto.response;

import java.time.LocalDateTime;

public record ObtenerBloqueoResponse(
        Integer id,
        Integer usuarioId,
        Integer usuarioBloqueadoId,
        LocalDateTime createdAt
) {
}