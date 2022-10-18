package tpi.backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UsuarioController {

        @GetMapping("login/ + {usuario} + / + {password}")
        public boolean getUsuario(@PathVariable String email, @PathVariable String password) {
            if(email == "admin@gmail.com" && password == "1234") {
                return true;
            }
            return false;
        }

}
