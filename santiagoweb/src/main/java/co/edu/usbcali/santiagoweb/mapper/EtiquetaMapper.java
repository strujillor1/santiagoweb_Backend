package co.edu.usbcali.santiagoweb.mapper;

import co.edu.usbcali.santiagoweb.domain.Etiqueta;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerEtiquetaResponse;
import co.edu.usbcali.santiagoweb.dto.request.CrearEtiquetaRequest;

import java.util.List;

public class EtiquetaMapper {

    public static ObtenerEtiquetaResponse etiquetaObtenerEtiquetaResponse(Etiqueta etiqueta) {

        return new ObtenerEtiquetaResponse(
                etiqueta.getId(),
                etiqueta.getNombre()
        );
    }

    public static List<ObtenerEtiquetaResponse> listaEtiquetaHaciaListaObtenerEtiquetaResponse(List<Etiqueta> etiquetas) {
        return etiquetas.stream().map(EtiquetaMapper::etiquetaObtenerEtiquetaResponse).toList();
    }

    public static Etiqueta crearEtiquetaRequestAEtiqueta(CrearEtiquetaRequest etiquetaRequest) {
        return Etiqueta.builder()
                .nombre(etiquetaRequest.nombre())
                .build();
    }
}
