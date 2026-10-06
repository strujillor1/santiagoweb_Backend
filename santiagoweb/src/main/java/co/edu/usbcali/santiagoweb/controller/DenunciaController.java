package co.edu.usbcali.santiagoweb.controller;

import co.edu.usbcali.santiagoweb.dto.request.CrearDenunciaRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerDenunciaResponse;
import co.edu.usbcali.santiagoweb.repository.DenunciaRepository;
import co.edu.usbcali.santiagoweb.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaRepository denunciaRepository;
    @Autowired
    private DenunciaService denunciaService;

    @GetMapping
    public List<ObtenerDenunciaResponse> obtenerDenuncias() {
        return denunciaService.obtenerDenuncias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObtenerDenunciaResponse> obtenerDenunciaPorId(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(denunciaService.obtenerDenunciaPorId(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<ObtenerDenunciaResponse> crearDenuncia(@RequestBody CrearDenunciaRequest request) throws Exception {
        ObtenerDenunciaResponse response =
                denunciaService.crearDenuncia(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}