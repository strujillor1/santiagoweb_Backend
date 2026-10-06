package co.edu.usbcali.santiagoweb.service;

import co.edu.usbcali.santiagoweb.domain.Denuncia;
import co.edu.usbcali.santiagoweb.domain.Publicacion;
import co.edu.usbcali.santiagoweb.domain.Usuario;
import co.edu.usbcali.santiagoweb.dto.request.CrearDenunciaRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerDenunciaResponse;
import co.edu.usbcali.santiagoweb.mapper.DenunciaMapper;
import co.edu.usbcali.santiagoweb.repository.DenunciaRepository;
import co.edu.usbcali.santiagoweb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ObtenerDenunciaResponse> obtenerDenuncias() {
        return DenunciaMapper.listaDenunciaHaciaListaObtenerDenunciaResponse(denunciaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ObtenerDenunciaResponse obtenerDenunciaPorId(Integer id) throws Exception {
        if (id == null || id <= 0) {
            throw new Exception("El valor del id no puede ser nulo, inferior o igual a cero");
        }
        Denuncia denuncia = denunciaRepository.findById(id)
                .orElseThrow(() -> new Exception("No existe la denuncia"));
        return DenunciaMapper.denunciaObtenerDenunciaResponse(denuncia);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ObtenerDenunciaResponse crearDenuncia(CrearDenunciaRequest request) throws Exception {

        if (request == null) {
            throw new Exception("El objeto a crear no puede ser nulo");
        }
        if (request.usuarioReportanteId() == null || request.usuarioReportanteId() <= 0) {
            throw new Exception("El usuario_reportante_id es obligatorio y debe ser mayor a cero");
        }
        if (request.motivo() == null || request.motivo().isBlank()) {
            throw new Exception("El motivo de la denuncia no puede estar vacío ni ser nulo");
        }
        if (request.usuarioReportadoId() != null && request.usuarioReportadoId() <= 0) {
            throw new Exception("El usuario_reportado_id debe ser mayor a cero");
        }

        Usuario reportante = usuarioRepository
                .findById(request.usuarioReportanteId())
                .orElseThrow(() ->
                        new Exception("No se ha encontrado el usuario reportante con id: " + request.usuarioReportanteId()));


        Denuncia denuncia = DenunciaMapper.crearDenunciaRequestADenuncia(request);

        denuncia.setUsuarioReportante(reportante);

        if (request.usuarioReportadoId() != null) {
            denuncia.setUsuarioReportado(Usuario.builder().id(request.usuarioReportadoId()).build());
        }

        if (request.publicacionId() != null) {
            denuncia.setPublicacion(Publicacion.builder().id(request.publicacionId()).build());
        }

        denuncia = denunciaRepository.save(denuncia);

        ObtenerDenunciaResponse denunciaResponse = DenunciaMapper.denunciaObtenerDenunciaResponse(denuncia);

        return denunciaResponse;
    }
}