package tpi.backend.Controller;

import org.springframework.web.bind.annotation.*;
import tpi.backend.Models.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RequestMapping("/usuario")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {


    @GetMapping("/test")
    public boolean test(){
        return true;
    }

    @GetMapping("/login/{usuario}/{password}")
    public boolean login(@PathVariable String usuario, @PathVariable String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blackjack", "root", "123456");
            PreparedStatement st = conn.prepareStatement("SELECT idusuario, usuario, password FROM usuario WHERE usuario = ? AND password = ? ");
            st.setString(1, usuario);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            return true;

        } catch (Exception exc) {
            return false;
        }
    }

    @GetMapping("registro/{usuario}/{password}")
    public boolean setUsuario(@PathVariable String usuario, @PathVariable String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blackjack", "root", "123456");
            PreparedStatement st = conn.prepareStatement("INSERT INTO usuario (usuario, password) VALUES (?, ?)");
            st.setString(1, usuario);
            st.setString(2, password);
            st.executeUpdate();
            return true;

        } catch (Exception exc) {
            return false;
        }
    }

}
