package clinica_odontologica.clinica.controller;


import clinica_odontologica.clinica.entity.Paciente;
import clinica_odontologica.clinica.Service.ServiceImp.PacienteServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteServiceImp pacienteService;


    @GetMapping("/todos")
    public ResponseEntity<ArrayList<Paciente>> mostrarPacientes (){
        ArrayList<Paciente> listarPaciente = pacienteService.mostrarTodos();
        return ResponseEntity.ok(listarPaciente);
    }

    @GetMapping("/{id}")
    public Optional<Paciente> mostrarPacientePorId(@PathVariable Integer id){
        return pacienteService.mostrarPorId(id);
    }

    @PostMapping
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardar(paciente);
    }

    @DeleteMapping("/{id}")
    public void eliminarPacientePorId(@PathVariable Integer id){
        pacienteService.eliminarPorId(id);
    }
}