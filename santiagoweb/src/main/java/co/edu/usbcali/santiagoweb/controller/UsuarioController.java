package co.edu.usbcali.santiagoweb.controller;


import co.edu.usbcali.santiagoweb.domain.Usuario;
import co.edu.usbcali.santiagoweb.dto.request.CrearUsuarioRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerUsuarioResponse;
import co.edu.usbcali.santiagoweb.mapper.UsuarioMapper;
import co.edu.usbcali.santiagoweb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.usbcali.santiagoweb.repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

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
                UsuarioMapper.listaUsuarioHaciaListaObtenerUsuarioResponse(usuarios);
        return usuariosResponse;
    }

    @GetMapping("/{id}")
    ResponseEntity<ObtenerUsuarioResponse> obtenerUsuarioPorId(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    @PostMapping("/crear")
    ResponseEntity<ObtenerUsuarioResponse> crearUsuario(@RequestBody CrearUsuarioRequest usuarioRequest) throws Exception {
        ObtenerUsuarioResponse usuarioResponse = usuarioService.crearUsuario(usuarioRequest);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }
}
