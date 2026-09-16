package co.edu.usbcali.santiagoweb.mapper;

import co.edu.usbcali.santiagoweb.domain.Denuncia;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerDenunciaResponse;

import java.util.List;

public class DenunciaMapper {

    // Hace el mapeo para obtener las denuncias y no traer el objeto de la base de datos
    public static ObtenerDenunciaResponse denunciaObtenerDenunciaResponse(Denuncia denuncia) {

        return new ObtenerDenunciaResponse(
                denuncia.getId(),
                denuncia.getUsuarioReportante().getId(),
                denuncia.getUsuarioReportado() != null ? denuncia.getUsuarioReportado().getId() : null,
                denuncia.getPublicacion() != null ? denuncia.getPublicacion().getId() : null,
                denuncia.getComentario() != null ? denuncia.getComentario().getId() : null,
                denuncia.getMotivo(),
                denuncia.getEstado().toString(),
                denuncia.getCreatedAt()
        );
    }

    public static List<ObtenerDenunciaResponse> listaDenunciaHaciaListaObtenerDenunciaResponse(List<Denuncia> denuncias) {
        return denuncias.stream().map(DenunciaMapper::denunciaObtenerDenunciaResponse).toList();
    }
}