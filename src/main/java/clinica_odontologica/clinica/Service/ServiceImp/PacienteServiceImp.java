package clinica_odontologica.clinica.Service.ServiceImp;

import clinica_odontologica.clinica.Service.IService;
import clinica_odontologica.clinica.entity.Domicilio;
import clinica_odontologica.clinica.entity.Paciente;
import clinica_odontologica.clinica.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImp implements IService<Paciente> {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    DomicilioServiceImp domicilioService;


    @Override
    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscar(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public boolean eliminar(Integer id) {
        Boolean respuesta;
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            respuesta = true;
        } else {
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        if (paciente.getNombre() != null) {
            paciente.setNombre(paciente.getNombre());
        }
        if (paciente.getApellido() != null) {
            paciente.setApellido(paciente.getApellido());
        }
        if (paciente.getDni() != null) {
            paciente.setDni(paciente.getDni());
        }
        if (paciente.getDomicilio() != null) {
            Domicilio domicilioActualizado = domicilioService.actualizar(paciente.getDomicilio());
            paciente.setDomicilio(domicilioActualizado);
        }
        return paciente;
    }

}
