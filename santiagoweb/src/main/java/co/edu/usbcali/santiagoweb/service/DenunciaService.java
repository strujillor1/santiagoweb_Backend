package co.edu.usbcali.santiagoweb.service;

import co.edu.usbcali.santiagoweb.dto.request.CrearDenunciaRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerDenunciaResponse;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DenunciaService {

    List<ObtenerDenunciaResponse> obtenerDenuncias();

    @Transactional(readOnly = true)
    ObtenerDenunciaResponse obtenerDenunciaPorId(Integer id) throws Exception;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    ObtenerDenunciaResponse crearDenuncia(CrearDenunciaRequest crearDenunciaRequest) throws Exception;
}


