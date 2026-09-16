package co.edu.usbcali.santiagoweb.controller;

import co.edu.usbcali.santiagoweb.domain.Bloqueo;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerBloqueoResponse;
import co.edu.usbcali.santiagoweb.mapper.BloqueoMapper;
import co.edu.usbcali.santiagoweb.repository.BloqueoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bloqueos")
public class BloqueoController {

    @Autowired
    private BloqueoRepository bloqueoRepository;

    @GetMapping("/ping")
    String pinpong() { return "pong"; }

    @GetMapping("/validar-estado")
    String validarEstado() { return "ok"; }

    @GetMapping("/obtener-bloqueo")
    List<Bloqueo> obtenerBloqueos() {return bloqueoRepository.findAll();}

    @GetMapping("/{id}")
    ResponseEntity<ObtenerBloqueoResponse> obtenerBloqueoPorId(@PathVariable Integer id) {
        Bloqueo bloqueo = bloqueoRepository.findById(id).orElse(null);

        if (bloqueo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(BloqueoMapper.bloqueoObtenerBloqueoResponse(bloqueo));
    }

    @GetMapping("/usuario/{usuarioId}")
    List<ObtenerBloqueoResponse> obtenerBloqueosPorUsuarioId(@PathVariable Integer usuarioId) {
        List<Bloqueo> bloqueos = bloqueoRepository.findByUsuario_Id(usuarioId);
        return BloqueoMapper.listaBloqueoHaciaListaObtenerBloqueoResponse(bloqueos);
    }
}
