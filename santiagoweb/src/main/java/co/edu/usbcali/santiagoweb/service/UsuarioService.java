package co.edu.usbcali.santiagoweb.service;

import co.edu.usbcali.santiagoweb.dto.request.CrearUsuarioRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerUsuarioResponse;

import java.util.List;

public interface UsuarioService {
    List<ObtenerUsuarioResponse> obtenerUsuarios();
    ObtenerUsuarioResponse obtenerUsuarioPorId(Integer id) throws Exception;
    ObtenerUsuarioResponse crearUsuario(CrearUsuarioRequest crearUsuario) throws Exception;
}
