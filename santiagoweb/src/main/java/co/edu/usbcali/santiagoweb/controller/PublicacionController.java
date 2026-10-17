package co.edu.usbcali.santiagoweb.controller;

import co.edu.usbcali.santiagoweb.dto.request.CrearPublicacionRequest;
import co.edu.usbcali.santiagoweb.dto.request.ModificarPublicacionRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerPublicacionResponse;
import co.edu.usbcali.santiagoweb.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    List<ObtenerPublicacionResponse> obtenerPublicaciones() {
        return publicacionService.obtenerPublicaciones();
    }

    @GetMapping("/{id}")
    ResponseEntity<ObtenerPublicacionResponse> obtenerPublicacionPorId(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));

    }

    @PostMapping("/crear")
    ResponseEntity<ObtenerPublicacionResponse> crearPublicacion(@RequestBody CrearPublicacionRequest publicacionRequest) throws Exception {
        ObtenerPublicacionResponse publicacionResponse =
                publicacionService.crearPublicacion(publicacionRequest);
        return new ResponseEntity<>(publicacionResponse, HttpStatus.CREATED);
    }

    @PutMapping("/modificar/{id}")
    ResponseEntity<ObtenerPublicacionResponse> modificarPublicacion(
            @PathVariable Integer id,
            @RequestBody ModificarPublicacionRequest modificarPublicacionRequest) throws Exception{
        return ResponseEntity.ok
                (publicacionService.modificarPublicacion(id, modificarPublicacionRequest));
    }

    @DeleteMapping("/eliminar/{id}")
    ResponseEntity<Void> eliminarPublicacion(@PathVariable Integer id) throws Exception {
        publicacionService.eliminarPublicacion(id);
        return ResponseEntity.noContent().build();
    }

}