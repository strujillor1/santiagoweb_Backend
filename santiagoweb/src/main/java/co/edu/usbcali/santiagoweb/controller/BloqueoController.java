package co.edu.usbcali.santiagoweb.controller;

import co.edu.usbcali.santiagoweb.domain.Bloqueo;
import co.edu.usbcali.santiagoweb.domain.Usuario;
import co.edu.usbcali.santiagoweb.repository.BloqueoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    List<Bloqueo> obtenerBloqueos() {
        return bloqueoRepository.findAll();
    }
}
