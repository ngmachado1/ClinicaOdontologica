package clinica_odontologica.clinica.Service.ServiceImp;

import clinica_odontologica.clinica.entity.Odontologo;
import clinica_odontologica.clinica.Repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OdontologoServiceImp {

    @Autowired
    private OdontologoRepository repository;


    public ArrayList<Odontologo> mostrarTodos() {
        return new ArrayList<>(repository.findAll());
    }

    public Optional<Odontologo> mostrarPorId(Integer id){
        return repository.findById(id);
    }

    public Odontologo guardar(Odontologo odontologo){
        return repository.save(odontologo);
    }

    public void eliminarPorId(Integer id){
        repository.deleteById(id);
    }
}
