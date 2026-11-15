package co.edu.usbcali.santiagoweb.service;

import co.edu.usbcali.santiagoweb.dto.request.CrearComentarioRequest;
import co.edu.usbcali.santiagoweb.dto.request.ModificarComentarioRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerComentarioResponse;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComentarioService {

    // Obtener todos los comentarios
    @Transactional(readOnly = true)
    List<ObtenerComentarioResponse> obtenerComentarios();

    // Obtener un comentario por su ID
    @Transactional(readOnly = true)
    ObtenerComentarioResponse obtenerComentarioPorId(Integer id) throws Exception;

    // Obtener comentarios asociados a una publicación
    @Transactional(readOnly = true)
    List<ObtenerComentarioResponse> obtenerComentariosPorPublicacionId(Integer publicacionId);

    // Obtener respuestas de un comentario padre
    @Transactional(readOnly = true)
    List<ObtenerComentarioResponse> obtenerRespuestasPorComentarioPadreId(Integer comentarioPadreId);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    ObtenerComentarioResponse crearComentario(CrearComentarioRequest crearComentarioRequest) throws Exception;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    ObtenerComentarioResponse modificarComentario(
            Integer id,
            ModificarComentarioRequest modificarComentarioRequest
    ) throws Exception;

}
