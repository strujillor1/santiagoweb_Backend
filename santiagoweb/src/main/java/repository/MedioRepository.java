package repository;

import co.edu.usbcali.santiagoweb.domain.Medio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedioRepository extends JpaRepository<Medio, Integer> {
}