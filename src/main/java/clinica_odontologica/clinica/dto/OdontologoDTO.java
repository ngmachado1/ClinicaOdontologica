package clinica_odontologica.clinica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OdontologoDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String matricula;
    private String telefono;

}