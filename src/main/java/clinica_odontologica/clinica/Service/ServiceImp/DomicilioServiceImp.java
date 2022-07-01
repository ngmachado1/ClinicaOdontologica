package clinica_odontologica.clinica.Service.ServiceImp;

import clinica_odontologica.clinica.entity.Domicilio;
import clinica_odontologica.clinica.Repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DomicilioServiceImp {

    @Autowired
    private DomicilioRepository repository;



    public ArrayList<Domicilio> mostrarTodos() {
        return new ArrayList<>(repository.findAll());
    }

    public Optional<Domicilio> mostrarPorId(Integer id){
        return repository.findById(id);
    }

    public Domicilio guardar(Domicilio domicilioDto){
        return repository.save(domicilioDto);
    }

    public void eliminarPorId(Integer id){
        repository.deleteById(id);
    }
}
