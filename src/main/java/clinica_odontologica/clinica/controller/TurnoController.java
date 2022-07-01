package clinica_odontologica.clinica.controller;


import clinica_odontologica.clinica.Service.ServiceImp.TurnoServiceImp;
import clinica_odontologica.clinica.entity.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("turnos")
public class TurnoController {

    @Autowired
    TurnoServiceImp turnoService;

    @GetMapping
    public ResponseEntity<ArrayList<Turno>> mostrarTurnos (){
        ArrayList<Turno> listarTurnos = turnoService.mostrarTodos();
        return ResponseEntity.ok(listarTurnos);
    }

    @GetMapping("/{id}")
    public Optional<Turno> mostrarTurnoPorId(@PathVariable Integer id){
        return turnoService.mostrarPorId(id);
    }

    @PostMapping
    public Turno guardarTurno(@RequestBody Turno turno){
        return turnoService.guardar(turno);
    }
}