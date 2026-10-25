package co.edu.usbcali.santiagoweb.repository;

import co.edu.usbcali.santiagoweb.domain.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {

    List<Publicacion> findByDeletedAtIsNull();

    List<Publicacion> findByDeletedAtIsNotNull();
}
