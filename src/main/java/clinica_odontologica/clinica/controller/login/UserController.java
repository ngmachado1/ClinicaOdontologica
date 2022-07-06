package clinica_odontologica.clinica.controller.login;

import clinica_odontologica.clinica.Service.ServiceImp.login.AppUserService;
import clinica_odontologica.clinica.entity.login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {
    @Autowired
    AppUserService service;

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @PostMapping("/usuarios/crear")
    public ResponseEntity<?> crear(@RequestBody User user){
        ResponseEntity<?> respuesta = ResponseEntity.badRequest().body(user);
        User usuario = service.crearUsuario(user);
        if (usuario != null){
            respuesta = org.springframework.http.ResponseEntity.ok(usuario);
        }
        return respuesta;
    }
}
