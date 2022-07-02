package clinica_odontologica.clinica.controller;


import clinica_odontologica.clinica.entity.Paciente;
import clinica_odontologica.clinica.Service.ServiceImp.PacienteServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteServiceImp pacienteService;


    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> mostrarPacientes (){
        List<Paciente> listarPaciente = pacienteService.buscarTodos();
        return ResponseEntity.ok(listarPaciente);
    }

    @GetMapping("/{id}")
    public Optional<Paciente> mostrarPacientePorId(@PathVariable Integer id){
        return pacienteService.buscar(id);
    }

    @PostMapping
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardar(paciente);
    }

    @DeleteMapping("/{id}")
    public void eliminarPacientePorId(@PathVariable Integer id){
        pacienteService.eliminar(id);
    }
}