package co.edu.usbcali.santiagoweb.repository;

import co.edu.usbcali.santiagoweb.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}