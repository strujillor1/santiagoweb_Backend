package co.edu.usbcali.santiagoweb.repository;

import co.edu.usbcali.santiagoweb.domain.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionEtiquetaRepository extends JpaRepository<Publicacion, Integer> {
}
