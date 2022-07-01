package clinica_odontologica.clinica.controller;

import clinica_odontologica.clinica.entity.Domicilio;
import clinica_odontologica.clinica.Service.ServiceImp.DomicilioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    DomicilioServiceImp domicilioService;

    @GetMapping("/todos")
    public ResponseEntity<ArrayList<Domicilio>> mostrarDomicilios (){
        ArrayList<Domicilio> listarDomicilios = domicilioService.mostrarTodos();
        return ResponseEntity.ok(listarDomicilios);
    }

    @GetMapping("/{id}")
    public Optional<Domicilio> mostrarDomicilioPorId(@PathVariable Integer id){
        return domicilioService.mostrarPorId(id);
    }

    @PostMapping("/guardar")
    public Domicilio guardarDomicilio(@RequestBody Domicilio domicilio){
        return domicilioService.guardar(domicilio);
    }

    @DeleteMapping("/{id}")
    public void eliminarDomicilioPorId(@PathVariable Integer id){
        domicilioService.eliminarPorId(id);
    }
}
