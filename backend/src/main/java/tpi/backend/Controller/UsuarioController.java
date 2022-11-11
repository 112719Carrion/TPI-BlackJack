package tpi.backend.Controller;

import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_b038d7c98f39121", "b902534b0a0d2e", "f00230c6");
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM usuario WHERE usuario = ? AND password = ?");
            preparedStatement.setString(1, usuario.getUsuario());
            preparedStatement.setString(2, usuario.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Usuario user = new Usuario(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> register(@RequestBody Usuario usuario){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_b038d7c98f39121", "b902534b0a0d2e", "f00230c6");
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO usuario (usuario, password) VALUES (?, ?)");
            preparedStatement.setString(1, usuario.getUsuario());
            preparedStatement.setString(2, usuario.getPassword());
            preparedStatement.execute();
            return ResponseEntity.ok(usuario);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
