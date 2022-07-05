package clinica_odontologica.clinica.Service.ServiceImp;

import clinica_odontologica.clinica.Service.Interfaces.TurnoServiceInterface;
import clinica_odontologica.clinica.config.ModelMapperConfig;
import clinica_odontologica.clinica.dto.OdontologoDTO;
import clinica_odontologica.clinica.dto.PacienteDTO;
import clinica_odontologica.clinica.dto.TurnoDTO;
import clinica_odontologica.clinica.entity.Turno;
import clinica_odontologica.clinica.Repository.TurnoRepository;
import clinica_odontologica.clinica.exceptions.BadRequestException;
import clinica_odontologica.clinica.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImp implements TurnoServiceInterface {

    @Autowired
    TurnoRepository repository;

    @Autowired
    PacienteServiceImp pacienteService;

    @Autowired
    OdontologoServiceImp odontologoServiceImp;

    @Autowired
    ModelMapperConfig mapper;


    @Override
    public TurnoDTO guardar(TurnoDTO entidad) throws BadRequestException, ResourceNotFoundException {
        TurnoDTO respuesta;
        if (entidad.getPaciente().getId() != null && entidad.getOdontologo() != null){
            PacienteDTO pacienteBuscado = pacienteService.buscarPorId(entidad.getPaciente().getId());
            OdontologoDTO odontologoBuscado = odontologoServiceImp.buscarPorId(entidad.getOdontologo().getId());
            if (verificarDisponibilidadTurno(odontologoBuscado.getId(), entidad.getDate())){
                //Creamos en BBDD
                Turno turno = mapper.getModelMapper().map(entidad, Turno.class);
                respuesta = mapper.getModelMapper().map(repository.save(turno), TurnoDTO.class);

                //Seteamos el odntologo y paciente al JSON de la respuesta
                respuesta.setPaciente(pacienteBuscado);
                respuesta.setOdontologo(odontologoBuscado);
            } else {
                throw new ResourceNotFoundException("El odontologo con id " + odontologoBuscado.getId() + " ya tiene un turno agendado en la fecha "+ entidad.getDate());
            }
        } else {
            throw new BadRequestException("Faltan introducir el id del paciente u odontologo para crear el turno");
        }
        return respuesta;
    }

    @Override
    public TurnoDTO buscar(Integer id) throws ResourceNotFoundException {
        return mapper.getModelMapper().map(repository.findById(id), TurnoDTO.class);
    }

    private Boolean verificarDisponibilidadTurno(Integer idOdontologo, LocalDate fechaTurno) {
        Boolean respuesta = true;
        List<Turno> listaTurnos = repository.findAll();
        for (Turno t: listaTurnos){
            TurnoDTO turnoDTO = mapper.getModelMapper().map(t,TurnoDTO.class);
            if (turnoDTO.getOdontologo().getId().equals(idOdontologo) && turnoDTO.getDate().equals(fechaTurno)){
                respuesta = false;
            }
        }
        return respuesta;
    }


    @Override
    public List<TurnoDTO> buscarTodos() throws ResourceNotFoundException{
        List<TurnoDTO> turnos = mapper.getModelMapper().map(repository.findAll(), List.class);
        if (turnos.size()<=0){
            throw new ResourceNotFoundException ("No hay turnos cargados");
        }
        return turnos;
    }

    @Override
    public String actualizar(TurnoDTO turnoDto) throws ResourceNotFoundException, BadRequestException {
        String respuesta;
        if (turnoDto.getId()!=null){
            Optional<Turno> turnoAModificar = repository.findById(turnoDto.getId());
            if (turnoAModificar.isPresent()){
                repository.save(this.actualizarEnBDD(turnoAModificar.get(), turnoDto));
                respuesta = "Actualización con éxito del turno número: " + turnoDto.getId();
            }
            else {
                throw new ResourceNotFoundException("No fue posible encontrar el turno en la base de datos");
            }
        } else {
            throw new BadRequestException("No se introdujo el id del turno a modificar");
        }
        return respuesta;
    }

    private Turno actualizarEnBDD(Turno turno, TurnoDTO turnoDTO){
        if (turnoDTO.getDate()!=null){
            turno.setDate(turnoDTO.getDate());
        }
        return turno;
    }

    @Override
    public String eliminar(Integer id) throws ResourceNotFoundException {
        String respuesta;
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            respuesta = "Eliminado con éxito el turno número: " + id;
        }
        else
            throw new ResourceNotFoundException("No fue encontrado el turno en la base de datos");

        return respuesta;
    }

    public List<TurnoDTO> turnosProxSemana() throws ResourceNotFoundException {
        LocalDate hoy = LocalDate.now();
        LocalDate proximaSemana = hoy.plusDays(7);
        List<TurnoDTO> listaTodosTurnos = this.buscarTodos();
        List<TurnoDTO> turnosProximaSemana = new ArrayList<>();
        for (TurnoDTO turno:listaTodosTurnos){
            if ((turno.getDate().isBefore(proximaSemana) && turno.getDate().isAfter(hoy)) || turno.getDate().isEqual(hoy) || turno.getDate().isEqual(proximaSemana)){
                turnosProximaSemana.add(turno);
            }
        }
        if (turnosProximaSemana.size()<=0)
            throw new ResourceNotFoundException("No hay turnos agendados para la próxima semana");
        return turnosProximaSemana;
    }
}