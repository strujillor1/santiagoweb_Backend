package co.edu.usbcali.santiagoweb.controller;


import co.edu.usbcali.santiagoweb.dto.response.ObtenerPublicacionResponse;
import co.edu.usbcali.santiagoweb.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    List<ObtenerPublicacionResponse> obtenerPublicaciones(){
        return publicacionService.obtenerPublicaciones();
    }

    @GetMapping("/{id}")
    ResponseEntity<ObtenerPublicacionResponse> obtenerPublicacionPorId(@PathVariable Integer id) throws Exception{
        return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));

    }
}
