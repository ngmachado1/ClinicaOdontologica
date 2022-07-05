package clinica_odontologica.clinica.dto;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class PacienteDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String sexo;
    private String dni;
    private String obraSocial;
    private LocalDateTime fechaIngreso;
    private DomicilioDTO domicilio;
}