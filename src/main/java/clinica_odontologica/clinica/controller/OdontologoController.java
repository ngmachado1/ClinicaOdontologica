package clinica_odontologica.clinica.controller;

import clinica_odontologica.clinica.Service.ServiceImp.OdontologoServiceImp;
import clinica_odontologica.clinica.entity.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("odontologos")
public class OdontologoController {

    @Autowired
    OdontologoServiceImp odontologoService;


    @GetMapping
    public ResponseEntity<List<Odontologo>> mostrarOdontologo (){
        List<Odontologo> listarOdontologos = odontologoService.buscarTodos();
        return ResponseEntity.ok(listarOdontologos);
    }

    @GetMapping("/{id}")
    public Optional<Odontologo> mostrarOdontologoPorId(@PathVariable Integer id){
        return odontologoService.buscar(id);
    }

    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardar(odontologo);
    }

    @DeleteMapping("/{id}")
    public void eliminarOdontologoPorId(@PathVariable Integer id){
        odontologoService.eliminar(id);
    }
}
