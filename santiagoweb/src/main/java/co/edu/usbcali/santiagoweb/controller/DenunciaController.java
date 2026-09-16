package co.edu.usbcali.santiagoweb.controller;

import co.edu.usbcali.santiagoweb.domain.Denuncia;
import co.edu.usbcali.santiagoweb.domain.enums.EstadoDenuncia;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerDenunciaResponse;
import co.edu.usbcali.santiagoweb.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    ResponseEntity<ObtenerDenunciaResponse> obtenerDenunciaPorId(@PathVariable Integer id) {
        Denuncia denuncia = denunciaRepository.findById(id).orElse(null);

        if (denuncia == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(co.edu.usbcali.santiagoweb.mapper.DenunciaMapper.denunciaObtenerDenunciaResponse(denuncia));
    }

    @GetMapping("/estado/{estado}")
    List<ObtenerDenunciaResponse> obtenerDenunciasPorEstado(@PathVariable EstadoDenuncia estado) {
        List<Denuncia> denuncias = denunciaRepository.findByEstado(estado);
        return co.edu.usbcali.santiagoweb.mapper.DenunciaMapper.listaDenunciaHaciaListaObtenerDenunciaResponse(denuncias);
    }
}