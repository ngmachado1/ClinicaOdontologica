package clinica_odontologica.clinica.controller;

import clinica_odontologica.clinica.Service.ServiceImp.OdontologoServiceImp;
import clinica_odontologica.clinica.dto.OdontologoDTO;
import clinica_odontologica.clinica.entity.Odontologo;
import clinica_odontologica.clinica.exceptions.BadRequestException;
import clinica_odontologica.clinica.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("odontologos")
public class OdontologoController implements ControllerInterface<OdontologoDTO> {

    @Autowired(required = true)
    OdontologoServiceImp odontologoService;

    @Override
    @PostMapping("/crear")
    public ResponseEntity<OdontologoDTO> crear(@RequestBody OdontologoDTO odontologo) {
        ResponseEntity<OdontologoDTO> respuesta = ResponseEntity.badRequest().body(odontologo);;
        OdontologoDTO odontologoInsertado = odontologoService.guardar(odontologo);
        if (odontologoInsertado != null){
            respuesta = ResponseEntity.ok(odontologoInsertado);
        }
        return respuesta;

    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDTO>> consultarTodos() throws ResourceNotFoundException
    {
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @Override
    public ResponseEntity<?> consultar(Integer id) throws ResourceNotFoundException, BadRequestException {
        return ResponseEntity.ok(odontologoService.buscar(id));
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(odontologoService.eliminar(id));
    }

    @Override
    @PutMapping()
    public ResponseEntity<String> actualizar(@RequestBody OdontologoDTO odontologo) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<String> respuesta;
        if (odontologo.getId() != null){
            respuesta = ResponseEntity.ok(odontologoService.actualizar(odontologo));
        } else{
            throw new BadRequestException("Falta el id del odontólogo");
        }
        return respuesta;
    }

    @GetMapping("/apellidos/{apellido}")
    public ResponseEntity<?> obtenerOdontologosPorApellidoEnElPathLike(@PathVariable String apellido){
        return ResponseEntity.ok(odontologoService.obtenerOdontologosPorSuApellidoLike(apellido));
    }

    // Lo hacemos con request param para tener otra forma de listarlos
    @GetMapping("/apellidos")
    public ResponseEntity<?> obtenerOdontologosPorApellidoLike(@RequestParam String apellido){
        return ResponseEntity.ok(odontologoService.obtenerOdontologosPorSuApellidoLike(apellido));
    }
}
