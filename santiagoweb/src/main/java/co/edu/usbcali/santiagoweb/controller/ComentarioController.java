package co.edu.usbcali.santiagoweb.controller;

import co.edu.usbcali.santiagoweb.dto.request.CrearComentarioRequest;
import co.edu.usbcali.santiagoweb.dto.request.ModificarComentarioRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerComentarioResponse;
import co.edu.usbcali.santiagoweb.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/obtener-comentario")
    List<ObtenerComentarioResponse> obtenerComentarios() {
        return comentarioService.obtenerComentarios();
    }

    @GetMapping("/{id}")
    ResponseEntity<ObtenerComentarioResponse> obtenerComentarioPorId(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(comentarioService.obtenerComentarioPorId(id));
        }


    @GetMapping("/publicacion/{publicacionId}")
    List<ObtenerComentarioResponse> obtenerComentariosPorPublicacionId(@PathVariable Integer publicacionId) {
        return comentarioService.obtenerComentariosPorPublicacionId(publicacionId);
    }

    @GetMapping("/padre/{comentarioPadreId}")
    List<ObtenerComentarioResponse> obtenerRespuestasPorComentarioPadreId(@PathVariable Integer comentarioPadreId) {
        return comentarioService.obtenerRespuestasPorComentarioPadreId(comentarioPadreId);
    }


    @PostMapping("/crear")
    ResponseEntity<ObtenerComentarioResponse> crearComentario(@RequestBody CrearComentarioRequest comentarioRequest)
            throws Exception {ObtenerComentarioResponse comentarioResponse =
                comentarioService.crearComentario(comentarioRequest);
        return new ResponseEntity<>(comentarioResponse, HttpStatus.CREATED);
    }

    @PutMapping("/modificar/{id}")
    ResponseEntity<ObtenerComentarioResponse> modificarComentario(@PathVariable Integer id,
            @RequestBody ModificarComentarioRequest modificarComentarioRequest) throws Exception {
        return ResponseEntity.ok(comentarioService.modificarComentario(id, modificarComentarioRequest));
    }

}
