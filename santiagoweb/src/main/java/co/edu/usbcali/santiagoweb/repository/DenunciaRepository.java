package co.edu.usbcali.santiagoweb.repository;

import co.edu.usbcali.santiagoweb.domain.Denuncia;
import co.edu.usbcali.santiagoweb.domain.enums.EstadoDenuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {

    List<Denuncia> findByEstado(EstadoDenuncia estado);
}
