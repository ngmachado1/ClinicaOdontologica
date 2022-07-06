package clinica_odontologica.clinica.Service.ServiceImp.login;

import clinica_odontologica.clinica.Repository.login.UserRepository;
import clinica_odontologica.clinica.entity.login.Roles;
import clinica_odontologica.clinica.entity.login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password"); //Genera el pass encriptado
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("password2");
        userRepository.save(new User("Natalia", "natalia", "natalia@digital.com", hashedPassword, Roles.ADMIN));
        userRepository.save(new User("Paula", "paula", "paula@digital.com", hashedPassword2, Roles.USER));
    }
}
