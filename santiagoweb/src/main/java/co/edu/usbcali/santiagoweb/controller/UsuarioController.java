package co.edu.usbcali.santiagoweb.controller;


import co.edu.usbcali.santiagoweb.domain.Usuario;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerUsuarioResponse;
import co.edu.usbcali.santiagoweb.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.usbcali.santiagoweb.repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/ping")
    String pingpong() {
        return "pong";
    }

    @GetMapping("/validar-stado")
    String validarEstado() {
        return "ok";
    }


    @GetMapping("/obtener-usuarios")
    List<ObtenerUsuarioResponse> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<ObtenerUsuarioResponse> usuariosResponse =
                UsuarioMapper.listaUsuariosHaciaListaObtenerUsuariosResponse(usuarios);
        return usuariosResponse;
    }

    @GetMapping("/{id}")
    ResponseEntity<ObtenerUsuarioResponse> obtenerUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        assert usuario != null;
        ObtenerUsuarioResponse usuarioResponse =
                UsuarioMapper.usuarioAObtenerUsuarioResponse(usuario);
        return ResponseEntity.ok(
                usuarioResponse
        );
    }
}
