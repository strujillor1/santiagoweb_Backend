package co.edu.usbcali.santiagoweb.mapper;

import co.edu.usbcali.santiagoweb.domain.Comentario;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerComentarioResponse;

import java.util.List;

public class ComentarioMapper {

    public static ObtenerComentarioResponse comentarioObtenerComentarioResponse(Comentario comentario) {


        return new ObtenerComentarioResponse(
                comentario.getId(), comentario.getPublicacion().getId(), comentario.getUsuario().getId(), comentario.getComentarioPadre() != null ? comentario.getComentarioPadre().getId() : null, comentario.getContenido(), comentario.getCreatedAt(), comentario.getUpdatedAt(), comentario.getDeletedAt()
        );
    }

    public static List<ObtenerComentarioResponse> listaComentarioHaciaListaObtenerComentarioResponse(List<Comentario> comentarios) {
        return comentarios.stream().map(ComentarioMapper::comentarioObtenerComentarioResponse).toList();
    }
}