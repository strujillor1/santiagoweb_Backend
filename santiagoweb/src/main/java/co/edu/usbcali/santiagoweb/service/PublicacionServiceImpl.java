package co.edu.usbcali.santiagoweb.service;

import co.edu.usbcali.santiagoweb.domain.Publicacion;
import co.edu.usbcali.santiagoweb.domain.Usuario;
import co.edu.usbcali.santiagoweb.domain.enums.PrivacidadPublicacion;
import co.edu.usbcali.santiagoweb.dto.request.CrearPublicacionRequest;
import co.edu.usbcali.santiagoweb.dto.request.ModificarPublicacionRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerPublicacionResponse;
import co.edu.usbcali.santiagoweb.mapper.PublicacionMapper;
import co.edu.usbcali.santiagoweb.repository.PublicacionRepository;
import co.edu.usbcali.santiagoweb.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ObtenerPublicacionResponse> obtenerPublicaciones() {
        return PublicacionMapper.
                listaPublicacionesHaciaListaObtenerPublicacionesResponse(
                        publicacionRepository.findAll()
                );
    }

    @Override
    @Transactional(readOnly = true)
    public ObtenerPublicacionResponse obtenerPublicacionPorId(Integer id) throws Exception {
        log.info("Se ha invocado el metodo obtener publicacionPorId con argumento:"+id);

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }
        if (id <= 0) {
            throw new Exception("El valor del id no puede ser inferior o igual a cero para buscar");
        }
        Optional<Publicacion> publicacionOptional = publicacionRepository.findById(id);
        if(publicacionOptional.isEmpty()) {
            throw new Exception("No existe la publicación");
        }

        return PublicacionMapper.publicacionAObtenerPublicacionResponse(publicacionOptional.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObtenerPublicacionResponse> obtenerPublicacionesVigentes() {
        return PublicacionMapper.
                listaPublicacionesHaciaListaObtenerPublicacionesResponse(
                        publicacionRepository.findByDeletedAtIsNull()
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ObtenerPublicacionResponse> obtenerPublicacionesEliminadas() {
        return PublicacionMapper.
                listaPublicacionesHaciaListaObtenerPublicacionesResponse(
                        publicacionRepository.findByDeletedAtIsNotNull()
                );
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ObtenerPublicacionResponse crearPublicacion(CrearPublicacionRequest crearPublicacionRequest) throws Exception {

        log.info("Se ha invocado metofo crearPublicacion con objeto crearPublicacionRequest: " +
                crearPublicacionRequest.toString());


        if (crearPublicacionRequest == null){
            throw new Exception("El objeto a crear no puede ser nulo");
        }

        if (crearPublicacionRequest.autorId() == null){
            throw new Exception("El autor_id de la publicación no puede ser nulo");
        }

        if (crearPublicacionRequest.autorId() <= 0){
            throw new Exception("El autor_id de la publicación debe ser mayor a cero");
        }

        if (crearPublicacionRequest.contenido() == null || crearPublicacionRequest.contenido().isBlank()){
            throw new Exception("El contenido de la publicación no puede estar vacío ni ser nulo");
        }

        if (crearPublicacionRequest.privacidadPublicacion() == null || crearPublicacionRequest.privacidadPublicacion().isBlank()){
            throw new Exception("La privacidad de la publicación no puede estar vacía ni ser nula");
        }

        Usuario autor = usuarioRepository
                .findById(crearPublicacionRequest.autorId())
                .orElseThrow(() ->
                        new Exception("No se ha encontrado el autor con el id: "+ crearPublicacionRequest.autorId() + "."));

// Validar que la privacidad se encuentre en el Enum
        if (Arrays.stream(PrivacidadPublicacion.values()).noneMatch(privacidad ->
                privacidad.toString().equalsIgnoreCase(crearPublicacionRequest.privacidadPublicacion()))) {
            throw new Exception("No se ha encontrado la privacidad-publicacion: " + crearPublicacionRequest.privacidadPublicacion() + ".");
        }

        Publicacion publicacion = PublicacionMapper.crearPublicacionRequestAPublicacion(crearPublicacionRequest);

        publicacion.setAutor(autor);

        publicacion = publicacionRepository.save(publicacion);

        ObtenerPublicacionResponse publicacionResponse = PublicacionMapper.publicacionAObtenerPublicacionResponse(publicacion);

        return publicacionResponse;
    }
    //Implementar la logica para modificar publicacion
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ObtenerPublicacionResponse modificarPublicacion(Integer id, ModificarPublicacionRequest modificarPublicacionRequest) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        if (id <= 0) {
            throw new Exception("El valor del id no puede ser inferior o igual a cero para modificar");
        }

        if (modificarPublicacionRequest == null) {
            throw new Exception("El objeto a modificar no puede ser nulo");
        }

        boolean hayContenido = modificarPublicacionRequest.contenido() != null
                && !modificarPublicacionRequest.contenido().isBlank();
        boolean hayPrivacidad = modificarPublicacionRequest.privacidadPublicacion() != null
                && !modificarPublicacionRequest.privacidadPublicacion().isBlank();

        // 1. Validaciones separadas con mensajes precisos
        if (!hayContenido) {
            throw new IllegalArgumentException("El contenido de la publicación es obligatorio.");
        }

        if (!hayPrivacidad) {
            throw new IllegalArgumentException("La privacidad de la publicación es obligatoria.");
        }

        Publicacion publicacion = publicacionRepository
                .findById(id)
                .orElseThrow(() -> new Exception("No existe la publicación con el id: " + id + "."));

        // Borrado lógico: no se permite modificar una publicación eliminada
        if (publicacion.getDeletedAt() != null) {
            throw new Exception("No se puede modificar una publicación eliminada");
        }

        // 2. Se eliminan los "if (hayContenido)" porque ya es seguro que vienen
        publicacion.setContenido(modificarPublicacionRequest.contenido());

        PrivacidadPublicacion privacidad = PrivacidadPublicacion
                .getPrivacidadPublicacion(modificarPublicacionRequest.privacidadPublicacion());

        if (privacidad == null) {
            throw new IllegalArgumentException("No se ha encontrado la privacidad-publicacion: "
                    + modificarPublicacionRequest.privacidadPublicacion() + ".");
        }

        publicacion.setPrivacidad(privacidad);

        // updatedAt se actualiza solo gracias al @PreUpdate de la entidad
        publicacion = publicacionRepository.save(publicacion);

        return PublicacionMapper.publicacionAObtenerPublicacionResponse(publicacion);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void eliminarPublicacion(Integer id) throws Exception {

        if (id == null) {
            throw new Exception("El id no puede ser nulo");
        }

        if (id <= 0) {
            throw new Exception("El valor del id no puede ser inferior o igual a cero para eliminar");
        }

        Publicacion publicacion = publicacionRepository
                .findById(id)
                .orElseThrow(() -> new Exception("No existe la publicación con el id: " + id + "."));

        if (publicacion.getDeletedAt() != null) {
            throw new Exception("La publicación ya se encuentra eliminada");
        }

        // Borrado lógico
        publicacion.setDeletedAt(LocalDateTime.now());

        publicacionRepository.save(publicacion);

        // Borrado fisico
        //publicacionRepository.delete(publicacion);

    }

}


