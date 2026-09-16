package co.edu.usbcali.santiagoweb.service;

import co.edu.usbcali.santiagoweb.dto.request.CrearEtiquetaRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerEtiquetaResponse;


import java.util.List;

public interface EtiquetaService {

    List<ObtenerEtiquetaResponse> obtenerEtiquetas();
    ObtenerEtiquetaResponse obtenerEtiquetaPorId(Integer id) throws  Exception;
    ObtenerEtiquetaResponse crearEtiqueta(CrearEtiquetaRequest crearEtiqueta) throws Exception;
}
