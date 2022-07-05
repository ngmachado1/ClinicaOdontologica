package clinica_odontologica.clinica.controller;

import clinica_odontologica.clinica.exceptions.BadRequestException;
import clinica_odontologica.clinica.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ControllerInterface <T>{
    @PostMapping("/crear")
    ResponseEntity<?> crear(@RequestBody T t) throws BadRequestException, ResourceNotFoundException;
    @GetMapping("/todos") ResponseEntity<?> consultarTodos() throws ResourceNotFoundException, BadRequestException;
    @GetMapping("/{id}") ResponseEntity<?> consultar(@PathVariable Integer id) throws ResourceNotFoundException, BadRequestException;
    @DeleteMapping("/{id}") ResponseEntity<?> eliminar(@PathVariable Integer id) throws Exception;
    @PutMapping ResponseEntity<?> actualizar(@RequestBody T t) throws BadRequestException, ResourceNotFoundException;
}
