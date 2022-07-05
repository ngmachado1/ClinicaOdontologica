package clinica_odontologica.clinica.controller;


import clinica_odontologica.clinica.Service.ServiceImp.TurnoServiceImp;
import clinica_odontologica.clinica.dto.TurnoDTO;
import clinica_odontologica.clinica.exceptions.BadRequestException;
import clinica_odontologica.clinica.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("turnos")
public class TurnoController implements ControllerInterface<TurnoDTO> {

    @Autowired(required = true)
    TurnoServiceImp service;

    @Override
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(service.guardar(turnoDTO));
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<?> consultarTodos() throws ResourceNotFoundException {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @Override
    public ResponseEntity<?> consultar(Integer id) throws ResourceNotFoundException, BadRequestException {
        return ResponseEntity.ok(service.buscar(id));
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.eliminar(id));
    }

    @Override
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException, BadRequestException{
        return ResponseEntity.ok(service.actualizar(turnoDTO));
    }

    @GetMapping("/proximaSemana")
    public ResponseEntity<?> turnosProximaSemana() throws ResourceNotFoundException {
        return ResponseEntity.ok(service.turnosProxSemana());
    }
}