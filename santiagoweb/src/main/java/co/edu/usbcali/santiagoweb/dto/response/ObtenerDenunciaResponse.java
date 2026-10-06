package co.edu.usbcali.santiagoweb.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record ObtenerDenunciaResponse(
        Integer id,
        @JsonProperty("usuario_reportante_id") Integer usuarioReportanteId,
        @JsonProperty("usuario_reportado_id") Integer usuarioReportadoId,
        @JsonProperty("publicacion_id") Integer publicacionId,
        @JsonProperty("comentario_id") Integer comentarioId,
        String motivo,
        String estado,
        @JsonProperty("created_at") LocalDateTime createdAt
) {
}