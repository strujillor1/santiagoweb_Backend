package co.edu.usbcali.santiagoweb.service;

import co.edu.usbcali.santiagoweb.domain.Comentario;
import co.edu.usbcali.santiagoweb.domain.Publicacion;
import co.edu.usbcali.santiagoweb.domain.Usuario;
import co.edu.usbcali.santiagoweb.dto.request.CrearComentarioRequest;
import co.edu.usbcali.santiagoweb.dto.request.ModificarComentarioRequest;
import co.edu.usbcali.santiagoweb.dto.response.ObtenerComentarioResponse;
import co.edu.usbcali.santiagoweb.mapper.ComentarioMapper;
import co.edu.usbcali.santiagoweb.repository.ComentarioRepository;
import co.edu.usbcali.santiagoweb.repository.PublicacionRepository;
import co.edu.usbcali.santiagoweb.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

    @Service
    @Slf4j
    public class ComentarioServiceImpl implements ComentarioService {

        @Autowired
        private ComentarioRepository comentarioRepository;

        @Autowired
        private PublicacionRepository publicacionRepository;

        @Autowired
        private UsuarioRepository usuarioRepository;

        // Obtener todos los comentarios
        @Override
        @Transactional(readOnly = true)
        public List<ObtenerComentarioResponse> obtenerComentarios() {
            return ComentarioMapper.
                    listaComentarioHaciaListaObtenerComentarioResponse(
                            comentarioRepository.findAll()
                    );
        }

        @Override
        @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
        public ObtenerComentarioResponse crearComentario(CrearComentarioRequest crearComentarioRequest) throws Exception {

            log.info("Se ha invocado el método crearComentario con objeto crearComentarioRequest: "
                    + crearComentarioRequest.toString());

            if (crearComentarioRequest == null) {
                throw new Exception("El objeto a crear no puede ser nulo");
            }

            if (crearComentarioRequest.publicacionId() == null) {
                throw new Exception("El publicacion_id del comentario no puede ser nulo");
            }

            if (crearComentarioRequest.publicacionId() <= 0) {
                throw new Exception("El publicacion_id del comentario debe ser mayor a cero");
            }

            if (crearComentarioRequest.usuarioId() == null) {
                throw new Exception("El usuario_id del comentario no puede ser nulo");
            }

            if (crearComentarioRequest.usuarioId() <= 0) {
                throw new Exception("El usuario_id del comentario debe ser mayor a cero");
            }

            if (crearComentarioRequest.contenido() == null
                    || crearComentarioRequest.contenido().isBlank()) {
                throw new Exception("El contenido del comentario no puede estar vacío ni ser nulo");
            }

            if (crearComentarioRequest.contenido().length() > 1000) {
                throw new Exception("El contenido del comentario no puede superar los 1000 caracteres");
            }

            Publicacion publicacion = publicacionRepository
                    .findById(crearComentarioRequest.publicacionId())
                    .orElseThrow(() ->
                            new Exception("No se ha encontrado la publicación con el id: "
                                    + crearComentarioRequest.publicacionId() + "."));

            Usuario usuario = usuarioRepository
                    .findById(crearComentarioRequest.usuarioId())
                    .orElseThrow(() ->
                            new Exception("No se ha encontrado el usuario con el id: "
                                    + crearComentarioRequest.usuarioId() + "."));

            Comentario comentarioPadre = null;

            // Validar si el comentario corresponde a una respuesta
            if (crearComentarioRequest.comentarioPadreId() != null) {

                if (crearComentarioRequest.comentarioPadreId() <= 0) {
                    throw new Exception("El comentario_padre_id debe ser mayor a cero");
                }

                comentarioPadre = comentarioRepository
                        .findById(crearComentarioRequest.comentarioPadreId())
                        .orElseThrow(() ->
                                new Exception("No se ha encontrado el comentario padre con el id: "
                                        + crearComentarioRequest.comentarioPadreId() + "."));

                // Validar que el comentario padre pertenezca a la misma publicación
                if (!comentarioPadre.getPublicacion().getId().equals(publicacion.getId())) {
                    throw new Exception("El comentario padre no pertenece a la publicación indicada");
                }
            }

            Comentario comentario = ComentarioMapper
                    .crearComentarioRequestAComentario(crearComentarioRequest);

            comentario.setPublicacion(publicacion);
            comentario.setUsuario(usuario);
            comentario.setComentarioPadre(comentarioPadre);

            comentario = comentarioRepository.save(comentario);

            ObtenerComentarioResponse comentarioResponse =
                    ComentarioMapper.comentarioObtenerComentarioResponse(comentario);

            return comentarioResponse;
        }

        // Obtener un comentario por su ID
        @Override
        @Transactional(readOnly = true)
        public ObtenerComentarioResponse obtenerComentarioPorId(Integer id) throws Exception {

            Comentario comentario = comentarioRepository.findById(id)
                    .orElseThrow(() -> new Exception("No se encontró el comentario con ID: " + id));

            return ComentarioMapper.comentarioObtenerComentarioResponse(comentario);
        }

        // Obtener comentarios asociados a una publicación
        @Override
        @Transactional(readOnly = true)
        public List<ObtenerComentarioResponse> obtenerComentariosPorPublicacionId(Integer publicacionId) {

            List<Comentario> comentarios = comentarioRepository
                    .findByPublicacion_IdOrderByCreatedAtAsc(publicacionId);

            return ComentarioMapper.listaComentarioHaciaListaObtenerComentarioResponse(comentarios);
        }

        // Obtener respuestas de un comentario padre
        @Override
        @Transactional(readOnly = true)
        public List<ObtenerComentarioResponse> obtenerRespuestasPorComentarioPadreId(Integer comentarioPadreId) {

            List<Comentario> comentarios = comentarioRepository
                    .findByComentarioPadre_IdOrderByCreatedAtAsc(comentarioPadreId);

            return ComentarioMapper.listaComentarioHaciaListaObtenerComentarioResponse(comentarios);
        }

        @Override
        @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
        public ObtenerComentarioResponse modificarComentario(Integer id, ModificarComentarioRequest modificarComentarioRequest) throws Exception {

            if (modificarComentarioRequest == null) {
                throw new Exception("El objeto a modificar no puede ser nulo");
            }

            log.info("Se ha invocado el método modificarComentario con id: " + id);

            if (id == null) {
                throw new Exception("El id del comentario no puede ser nulo");
            }

            if (id <= 0) {
                throw new Exception("El id del comentario debe ser mayor a cero");
            }

            if (modificarComentarioRequest.contenido() == null
                    || modificarComentarioRequest.contenido().isBlank()) {
                throw new Exception("El contenido del comentario no puede estar vacío ni ser nulo");
            }

            if (modificarComentarioRequest.contenido().length() > 1000) {
                throw new Exception("El contenido del comentario no puede superar los 1000 caracteres");
            }

            Comentario comentario = comentarioRepository
                    .findById(id)
                    .orElseThrow(() ->
                            new Exception("No se ha encontrado el comentario con el id: " + id + "."));

            if (comentario.getDeletedAt() != null) {
                throw new Exception("No se puede modificar un comentario que ha sido eliminado");
            }

            comentario.setContenido(modificarComentarioRequest.contenido());

            comentario = comentarioRepository.save(comentario);

            ObtenerComentarioResponse comentarioResponse =
                    ComentarioMapper.comentarioObtenerComentarioResponse(comentario);

            return comentarioResponse;
        }


}
