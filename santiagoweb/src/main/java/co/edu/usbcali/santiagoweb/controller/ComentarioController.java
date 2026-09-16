package co.edu.usbcali.santiagoweb.controller;

import co.edu.usbcali.santiagoweb.domain.Comentario;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerComentarioResponse;
import co.edu.usbcali.santiagoweb.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    ResponseEntity<ObtenerComentarioResponse> obtenerComentarioPorId(@PathVariable Integer id) {
        Comentario comentario = comentarioRepository.findById(id).orElse(null);

        if (comentario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(co.edu.usbcali.santiagoweb.mapper.ComentarioMapper.comentarioObtenerComentarioResponse(comentario));
    }

    @GetMapping("/publicacion/{publicacionId}")
    List<ObtenerComentarioResponse> obtenerComentariosPorPublicacionId(@PathVariable Integer publicacionId) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacion_IdOrderByCreatedAtAsc(publicacionId);
        return co.edu.usbcali.santiagoweb.mapper.ComentarioMapper.listaComentarioHaciaListaObtenerComentarioResponse(comentarios);
    }

    @GetMapping("/padre/{comentarioPadreId}")
    List<ObtenerComentarioResponse> obtenerRespuestasPorComentarioPadreId(@PathVariable Integer comentarioPadreId) {
        List<Comentario> comentarios = comentarioRepository.findByComentarioPadre_IdOrderByCreatedAtAsc(comentarioPadreId);
        return co.edu.usbcali.santiagoweb.mapper.ComentarioMapper.listaComentarioHaciaListaObtenerComentarioResponse(comentarios);
    }
}
