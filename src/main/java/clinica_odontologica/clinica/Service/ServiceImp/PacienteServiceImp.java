package clinica_odontologica.clinica.Service.ServiceImp;

import clinica_odontologica.clinica.Service.IService;
import clinica_odontologica.clinica.Service.Interfaces.PacienteServiceInterface;
import clinica_odontologica.clinica.config.ModelMapperConfig;
import clinica_odontologica.clinica.dto.DomicilioDTO;
import clinica_odontologica.clinica.dto.PacienteDTO;
import clinica_odontologica.clinica.entity.Domicilio;
import clinica_odontologica.clinica.entity.Paciente;
import clinica_odontologica.clinica.Repository.PacienteRepository;
import clinica_odontologica.clinica.exceptions.BadRequestException;
import clinica_odontologica.clinica.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImp implements PacienteServiceInterface {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    ModelMapperConfig mapper;

    @Autowired
    DomicilioServiceImp domicilioService;

    @Override
    public PacienteDTO guardar(PacienteDTO entidad){
        Paciente paciente = mapper.getModelMapper().map(entidad, Paciente.class);
        repository.save(paciente);
        return mapper.getModelMapper().map(paciente, PacienteDTO.class);
    }

    @Override
    public PacienteDTO buscar(Integer id) throws ResourceNotFoundException {
    if(id != null){
        throw new ResourceNotFoundException("No hay un paciente con ese ID");
    }
        return mapper.getModelMapper().map(repository.findById(id), PacienteDTO.class);
    }


    @Override
    public List<PacienteDTO> buscarTodos() throws BadRequestException {
        List<Paciente> lista = repository.findAll();
        if (lista.size() == 0){
            throw new BadRequestException("No hay pacientes aún cargados en la base de datos");
        }
        return mapper.getModelMapper().map(lista, List.class);
    }

    @Override
    public String actualizar(PacienteDTO entidad) throws ResourceNotFoundException {
        String respuesta;
        Optional<Paciente> pacienteAModificar = repository.findById(entidad.getId());
        if(pacienteAModificar.isPresent()){
            repository.save(this.actualizarEnBDD(pacienteAModificar.get(), entidad));
            respuesta = "Actualización con éxito del paciente con id: " + entidad.getId();
        } else {
            throw new ResourceNotFoundException("No se logró actualizar el paciente. El paciente con id " + entidad.getId() +" no fue encontrado en la base de datos");
        }
        return respuesta;
    }

    private Paciente actualizarEnBDD(Paciente paciente, PacienteDTO pacienteDto) throws ResourceNotFoundException{
        if (pacienteDto.getNombre() != null) {
            paciente.setNombre(pacienteDto.getNombre());
        }
        if (pacienteDto.getApellido() != null) {
            paciente.setApellido(pacienteDto.getApellido());
        }
        if (pacienteDto.getDni() != null) {
            paciente.setDni(pacienteDto.getDni());
        }
        if (pacienteDto.getDomicilio() != null) {
            DomicilioDTO domicilioActualizado = domicilioService.actualizar(pacienteDto.getDomicilio());
            paciente.setDomicilio(mapper.getModelMapper().map(domicilioActualizado, Domicilio.class));
        }
        return paciente;
    }

    @Override
    public String eliminar(Integer id) throws ResourceNotFoundException{
        String respuesta;
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            respuesta = "Eliminado con éxito";
        } else {
            throw new ResourceNotFoundException("No se logró eliminar el paciente. El id " + id +" no fue encontrado en la base de datos.");
        }
        return respuesta;
    }

    public PacienteDTO buscarPorId(Integer id) throws ResourceNotFoundException {
        Paciente pacienteRespuesta = repository.findById(id).orElse(null);
        if (pacienteRespuesta != null){
            return mapper.getModelMapper().map(pacienteRespuesta, PacienteDTO.class);
        } else {
            throw new ResourceNotFoundException ("No fue encontrado el paciente con id " + id);
        }
    }

}
