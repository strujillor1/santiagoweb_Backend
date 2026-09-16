package co.edu.usbcali.santiagoweb.mapper;

import co.edu.usbcali.santiagoweb.domain.Bloqueo;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerBloqueoResponse;

import java.util.List;

public class BloqueoMapper {

    // Hace el mapeo para obtener los bloqueos y no traer el objeto de la base de datos
    public static ObtenerBloqueoResponse bloqueoObtenerBloqueoResponse(Bloqueo bloqueo) {

        if (bloqueo == null) {
            return null;
        }

        return new ObtenerBloqueoResponse(
                bloqueo.getId(),
                bloqueo.getUsuario().getId(),
                bloqueo.getUsuarioBloqueado().getId(),
                bloqueo.getCreatedAt()
        );
    }

    public static List<ObtenerBloqueoResponse> listaBloqueoHaciaListaObtenerBloqueoResponse(List<Bloqueo> bloqueos) {
        return bloqueos.stream().map(BloqueoMapper::bloqueoObtenerBloqueoResponse).toList();
    }
}