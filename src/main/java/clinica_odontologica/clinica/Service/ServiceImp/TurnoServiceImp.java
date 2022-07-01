package clinica_odontologica.clinica.Service.ServiceImp;

import clinica_odontologica.clinica.entity.Turno;
import clinica_odontologica.clinica.Repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TurnoServiceImp {

    @Autowired
    private TurnoRepository repository;

    public ArrayList<Turno> mostrarTodos() {
        return new ArrayList<>(repository.findAll());
    }

    public Optional<Turno> mostrarPorId(Integer id){
        return repository.findById(id);
    }

    public Turno guardar(Turno turno){
        return repository.save(turno);
    }

    public void eliminarPorId(Integer id){
        repository.deleteById(id);
    }
}