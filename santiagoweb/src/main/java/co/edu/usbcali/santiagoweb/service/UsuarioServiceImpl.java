package co.edu.usbcali.santiagoweb.service;

import co.edu.usbcali.santiagoweb.domain.Usuario;
import co.edu.usbcali.santiagoweb.dto.request.CrearUsuarioRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerUsuarioResponse;
import co.edu.usbcali.santiagoweb.mapper.UsuarioMapper;
import co.edu.usbcali.santiagoweb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<ObtenerUsuarioResponse> obtenerUsuarios() {
        List<Usuario> todoslosUsuarios = usuarioRepository.findAll();
        List<ObtenerUsuarioResponse> usuariosResponses =
                UsuarioMapper.listaUsuarioHaciaListaObtenerUsuarioResponse(todoslosUsuarios);
        return usuariosResponses;
    }

    @Override
    @Transactional(readOnly = true)
    public ObtenerUsuarioResponse obtenerUsuarioPorId(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }
        if (id <= 0) {
            throw new Exception("El valor del id no puede ser inferior o igual a cero");
        }
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new Exception("No se ha encontrado el Usuario con el id: " + id);
        }
        return UsuarioMapper.usuarioAObtenerUsuarioResponse(usuarioOptional.get());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ObtenerUsuarioResponse crearUsuario(CrearUsuarioRequest crearUsuario) throws Exception {

        if (crearUsuario == null) {
            throw new Exception("El objeto a crear no puede ser nulo");
        }
        if (crearUsuario.email() == null || crearUsuario.email().isBlank()) {
            throw new Exception("El email del usuario no puede estar vacio ni ser nulo");
        }
        if (crearUsuario.username() == null || crearUsuario.username().isBlank()) {
            throw new Exception("El username no puede estar vacio ni ser nulo");
        }
        if (crearUsuario.passwordHash() == null || crearUsuario.passwordHash().isBlank()) {
            throw new Exception("El password no puede estar vacio ni ser nulo");
        }

        if (usuarioRepository.existsByEmail(crearUsuario.email())) {
            throw new Exception("El email ingresado ya se encuentra registrado");
        }
        if (usuarioRepository.existsByUsername(crearUsuario.username())) {
            throw new Exception("El username ingresado ya se encuentra en uso");
        }

        Usuario usuario = UsuarioMapper.crearUsuarioRequestAUsuario(crearUsuario);
        usuario = usuarioRepository.save(usuario);

        return UsuarioMapper.usuarioAObtenerUsuarioResponse(usuario);
    }
}