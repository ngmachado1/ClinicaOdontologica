package clinica_odontologica.clinica.Service.ServiceImp;

import clinica_odontologica.clinica.Service.IService;
import clinica_odontologica.clinica.entity.Domicilio;
import clinica_odontologica.clinica.Repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioServiceImp implements IService<Domicilio> {

    @Autowired
    private DomicilioRepository repository;


    @Override
    public Domicilio guardar(Domicilio domicilio) {
        return null;
    }

    @Override
    public Optional<Domicilio> buscar(Integer id) {
        return Optional.empty();
    }
    @Override
    public List<Domicilio> buscarTodos() {
        return null;
    }
    @Override
    public boolean eliminar(Integer id) {
        return true;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        if (domicilio.getCalle() != null) {
            domicilio.setCalle(domicilio.getCalle());
        }
        if (domicilio.getNumero() != null) {
            domicilio.setNumero(domicilio.getNumero());
        }
        if (domicilio.getLocalidad() != null) {
            domicilio.setLocalidad(domicilio.getLocalidad());
        }
        if (domicilio.getProvincia() != null) {
            domicilio.setProvincia(domicilio.getProvincia());
        }
        return domicilio;
    }


}