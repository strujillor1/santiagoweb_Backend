package co.edu.usbcali.santiagoweb.repository;


import co.edu.usbcali.santiagoweb.domain.Bloqueo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloqueoRepository  extends JpaRepository<Bloqueo, Integer> {
    List<Bloqueo> findByUsuario_Id(Integer usuarioId);
}