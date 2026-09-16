package co.edu.usbcali.santiagoweb.mapper;

import co.edu.usbcali.santiagoweb.domain.Usuario;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerUsuarioResponse;


import java.util.List;

public class UsuarioMapper {
    public static ObtenerUsuarioResponse usuarioAObtenerUsuarioResponse(Usuario usuario){
        return new ObtenerUsuarioResponse(
                usuario.getId(), usuario.getEmail(), usuario.getUsername(), usuario.getLastLogin());

    }
    public static List<ObtenerUsuarioResponse> listaUsuariosHaciaListaObtenerUsuariosResponse(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioMapper::usuarioAObtenerUsuarioResponse).toList();
    }

}
