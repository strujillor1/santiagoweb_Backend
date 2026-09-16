package co.edu.usbcali.santiagoweb.dto.response;

import java.time.LocalDateTime;

public record ObtenerDenunciaResponse(
        Integer id,
        Integer usuarioReportanteId,
        Integer usuarioReportadoId,
        Integer publicacionId,
        Integer comentarioId,
        String motivo,
        String estado,
        LocalDateTime createdAt
) {
}