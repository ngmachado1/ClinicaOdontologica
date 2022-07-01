package clinica_odontologica.clinica.Service.ServiceImp;

import clinica_odontologica.clinica.entity.Paciente;
import clinica_odontologica.clinica.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PacienteServiceImp {

    @Autowired
    private PacienteRepository repository;


    public ArrayList<Paciente> mostrarTodos() {
        return new ArrayList<>(repository.findAll());
    }

    public Optional<Paciente> mostrarPorId(Integer id){
        return repository.findById(id);
    }

    public Paciente guardar(Paciente paciente){
        return repository.save(paciente);
    }

    public void eliminarPorId(Integer id){
        repository.deleteById(id);
    }
}
