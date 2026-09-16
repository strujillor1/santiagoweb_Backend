package co.edu.usbcali.santiagoweb.controller;

import co.edu.usbcali.santiagoweb.dto.request.CrearEtiquetaRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerEtiquetaResponse;
import co.edu.usbcali.santiagoweb.service.EtiquetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetaController {

    @Autowired
    private EtiquetaService etiquetaService;

    @GetMapping("/ping")
    String pingpong() {
        return "pong";
    }

    @GetMapping("/validar-estado")
    String validarEstado() {
        return "ok";
    }


    @GetMapping("/obtener-etiquetas")
    List<ObtenerEtiquetaResponse> obtenerEtiquetas() {
        return etiquetaService.obtenerEtiquetas();
    }

    @GetMapping("/{id}")
    ResponseEntity<ObtenerEtiquetaResponse> obtenerEtiquetaPorId(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(etiquetaService.obtenerEtiquetaPorId(id));
    }

    @PostMapping("/crear")
    ResponseEntity<ObtenerEtiquetaResponse> crearEtiqueta(@RequestBody CrearEtiquetaRequest etiquetaRequest) throws Exception{
        ObtenerEtiquetaResponse etiquetaResponse =
                etiquetaService.crearEtiqueta(etiquetaRequest);
        return new ResponseEntity<>(etiquetaResponse, HttpStatus.CREATED);
    }
}