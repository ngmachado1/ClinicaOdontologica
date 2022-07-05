package clinica_odontologica.clinica.controller;


import clinica_odontologica.clinica.dto.PacienteDTO;
import clinica_odontologica.clinica.entity.Paciente;
import clinica_odontologica.clinica.Service.ServiceImp.PacienteServiceImp;
import clinica_odontologica.clinica.exceptions.BadRequestException;
import clinica_odontologica.clinica.exceptions.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController implements ControllerInterface<PacienteDTO> {

    @Autowired
    PacienteServiceImp pacienteService;


    private static final Logger logger = Logger.getLogger(PacienteController.class);

    @Override
    @PostMapping("/crear")
    public ResponseEntity<PacienteDTO> crear(@RequestBody PacienteDTO paciente) {
        ResponseEntity<PacienteDTO> response = null;
        paciente.setFechaIngreso(LocalDateTime.now());
        PacienteDTO pacienteInsertado = pacienteService.guardar(paciente);
        if (pacienteInsertado != null) {
            response = ResponseEntity.ok(pacienteInsertado);
        }

        return response;
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDTO>> consultarTodos() throws BadRequestException {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @Override
    public ResponseEntity<?> consultar(Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(pacienteService.eliminar(id));
    }

    @Override
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody PacienteDTO paciente) throws BadRequestException,ResourceNotFoundException {
        ResponseEntity<String> respuesta;
        if(paciente.getId() != null ){
            respuesta = ResponseEntity.ok(pacienteService.actualizar(paciente));
        } else {
            throw new BadRequestException("Id del paciente o del domicilio faltantes");
        }
        return respuesta;
    }
}