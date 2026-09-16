package co.edu.usbcali.santiagoweb.service;

import co.edu.usbcali.santiagoweb.domain.Etiqueta;
import co.edu.usbcali.santiagoweb.dto.request.CrearEtiquetaRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerEtiquetaResponse;
import co.edu.usbcali.santiagoweb.mapper.EtiquetaMapper;
import co.edu.usbcali.santiagoweb.repository.EtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EtiquetaServiceImpl implements EtiquetaService {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @Override
    public List<ObtenerEtiquetaResponse> obtenerEtiquetas() {
        List<Etiqueta> todaslasEtiquetas = etiquetaRepository.findAll();
        List<ObtenerEtiquetaResponse> etiquetasResponses =
                EtiquetaMapper.listaEtiquetaHaciaListaObtenerEtiquetaResponse(todaslasEtiquetas);
        return etiquetasResponses;

        //return EtiquetaMapper.listaEtiquetaHaciaListaObtenerEtiquetaResponse(todaslasEtiquetas);
    }

    @Override
    @Transactional(readOnly = true)
    public ObtenerEtiquetaResponse obtenerEtiquetaPorId(Integer id) throws Exception {
        //Validar que id no sea nulo, ni vacio ni sea valor inferior a cero

        if(id == null){
            throw new Exception("El id no puede ser nulo");
        }
        if (id <=0){
            throw new Exception("El valor del id no puede ser inferior o igual a cero");
        }
        Optional<Etiqueta> etiquetaOptional = etiquetaRepository.findById(id);
        if (etiquetaOptional.isEmpty()){
            throw new Exception("No se ha encontrado la Etiqueta con el id: "+id);
        }
        ObtenerEtiquetaResponse etiquetaResponse =
                EtiquetaMapper.etiquetaObtenerEtiquetaResponse(etiquetaOptional.get());
        return etiquetaResponse;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ObtenerEtiquetaResponse crearEtiqueta(CrearEtiquetaRequest crearEtiqueta) throws Exception {

        if (crearEtiqueta == null){
            throw new Exception("El objeto a crear no puede ser nulo");

        }

        if (crearEtiqueta.nombre() == null || crearEtiqueta.nombre().isBlank()){
            throw new Exception("El nombre de la etiqueta no puede estar vacio ni ser nulo");
        }

        Boolean existeEtiquetaPorNombre = etiquetaRepository.existByNombre(crearEtiqueta.nombre());
        if (existeEtiquetaPorNombre){
            throw new Exception("El nombre de la etiquer¿ta ya existe");
        }


        Etiqueta etiqueta = EtiquetaMapper.crearEtiquetaRequestAEtiqueta(crearEtiqueta);

        etiqueta = etiquetaRepository.save(etiqueta);

        ObtenerEtiquetaResponse etiquetaResponse = EtiquetaMapper. etiquetaObtenerEtiquetaResponse(etiqueta);

        return etiquetaResponse;
    }
}
