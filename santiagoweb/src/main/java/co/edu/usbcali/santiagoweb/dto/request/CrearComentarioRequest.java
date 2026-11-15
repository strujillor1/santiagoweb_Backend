package co.edu.usbcali.santiagoweb.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CrearComentarioRequest (

    @JsonProperty("publicacion_id") Integer publicacionId,
    @JsonProperty("usuario_id") Integer usuarioId,
    @JsonProperty("comentario_padre_id") Integer comentarioPadreId,
    String contenido

){

}
