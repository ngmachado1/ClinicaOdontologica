package clinica_odontologica.clinica.Service;

import clinica_odontologica.clinica.exceptions.BadRequestException;
import clinica_odontologica.clinica.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IService<T> {
    T guardar(T t) throws Exception;
    T buscar (Integer id) throws Exception;
    List<T> buscarTodos() throws Exception;
    String actualizar(T t) throws Exception;
    String eliminar(Integer id) throws Exception;
}
