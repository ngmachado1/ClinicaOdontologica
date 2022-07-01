package clinica_odontologica.clinica.Repository;

import clinica_odontologica.clinica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {
}
