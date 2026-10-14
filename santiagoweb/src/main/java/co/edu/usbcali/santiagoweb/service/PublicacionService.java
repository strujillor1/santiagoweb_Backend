package co.edu.usbcali.santiagoweb.service;

import co.edu.usbcali.santiagoweb.dto.request.CrearPublicacionRequest;
import co.edu.usbcali.santiagoweb.dto.request.ModificarPublicacionRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerPublicacionResponse;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PublicacionService {
    List<ObtenerPublicacionResponse> obtenerPublicaciones();

    @Transactional(readOnly = true)
    ObtenerPublicacionResponse obtenerPublicacionPorId(Integer id) throws Exception;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    ObtenerPublicacionResponse crearPublicacion(CrearPublicacionRequest crearPublicacionRequest) throws Exception;

    // Método para modificar una publicación existente en bases de datos
    ObtenerPublicacionResponse modificarPublicacion(Integer id, ModificarPublicacionRequest modificarPublicacionRequest) throws Exception;

    void eliminarPublicacion(Integer id) throws Exception;
}
