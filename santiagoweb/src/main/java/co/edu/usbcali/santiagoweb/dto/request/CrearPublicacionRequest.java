package co.edu.usbcali.santiagoweb.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CrearPublicacionRequest(
        @JsonProperty("autor_id") Integer autorId,
        String contenido,
        @JsonProperty("privacidad_publicacion") String privacidadPublicacion
){

}
