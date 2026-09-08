package co.edu.usbcali.santiagoweb.controller;


import co.edu.usbcali.santiagoweb.domain.Comentario;
import co.edu.usbcali.santiagoweb.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping("/ping")
    String pingpong() {
        return "pong";
    }

    @GetMapping("/validar-stado")
    String validarEstado() {
        return "ok";
    }


    @GetMapping("/obtener-comentario")
    List<Comentario> obtenerComentarios() {
        return comentarioRepository.findAll();
    }

}
