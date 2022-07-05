package clinica_odontologica.clinica.Service.Interfaces;

import clinica_odontologica.clinica.Service.IService;
import clinica_odontologica.clinica.dto.OdontologoDTO;

import java.util.Set;

public interface OdontologoServiceInterface  extends IService<OdontologoDTO> {
    public Set<OdontologoDTO> obtenerOdontologosPorSuApellidoLike(String apellidoOdontologo);
}
