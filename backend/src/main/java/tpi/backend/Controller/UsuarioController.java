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
    public int login(@PathVariable String usuario, @PathVariable String password) {
        try {
            int id = 0;
            Connection conn = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_b038d7c98f39121", "b902534b0a0d2e", "f00230c6");
            PreparedStatement st = conn.prepareStatement("SELECT idusuario FROM usuario WHERE usuario = ? AND password = ? ");
            st.setString(1, usuario);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idusuario");
            }
            return id;

        } catch (Exception exc) {
            return 0;
        }
    }

    @GetMapping("registro/{usuario}/{password}")
    public boolean setUsuario(@PathVariable String usuario, @PathVariable String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_b038d7c98f39121", "b902534b0a0d2e", "f00230c6");
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
