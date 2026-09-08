package co.edu.usbcali.santiagoweb.controller;

import co.edu.usbcali.santiagoweb.domain.Denuncia;
import co.edu.usbcali.santiagoweb.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @GetMapping("/ping")
    String pingpong() {
        return "pong";
    }

    @GetMapping("/validar-stado")
    String validarEstado() {
        return "ok";
    }


    @GetMapping("/obtener-denuncia")
    List<Denuncia> obtenerDenuncias() {
        return denunciaRepository.findAll();
    }

}
