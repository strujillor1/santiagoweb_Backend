package co.edu.usbcali.santiagoweb.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CrearDenunciaRequest(

        @JsonProperty("usuario_reportante_id") Integer usuarioReportanteId,
        @JsonProperty("usuario_reportado_id") Integer usuarioReportadoId,
        @JsonProperty("publicacion_id") Integer publicacionId,
        @JsonProperty("comentario_id") Integer comentarioId,
        String motivo
) {}