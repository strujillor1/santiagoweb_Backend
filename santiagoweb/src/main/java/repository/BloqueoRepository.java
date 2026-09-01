package repository;


import co.edu.usbcali.santiagoweb.domain.Bloqueo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueoRepository  extends JpaRepository<Bloqueo, Integer> {
}