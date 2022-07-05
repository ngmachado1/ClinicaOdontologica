package clinica_odontologica.clinica.Repository;

import clinica_odontologica.clinica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {
    /**Buscar odontologo por apellido*/
    @Query("from Odontologo o where o.apellido like %:apellido%")
    Set<Odontologo> getOdontologoByApellidoLike(@Param("apellido")String apellidoOdontologo);
}
