package tpi.backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tpi.backend.Models.Carta;

import java.sql.*;
import java.util.ArrayList;

@RestController
public class Controller {

    private ArrayList<Carta> mazoCartas;

    private ArrayList<Carta> mazoJugador = new ArrayList<>();
    private ArrayList<Carta> mazoCrupier = new ArrayList<>();

    private ArrayList<Carta> mazoJugadorAnterior = new ArrayList<>();

    private ArrayList<Carta> mazoCrupierAnterior = new ArrayList<>();



    public Controller(){
        mazoCartas = new ArrayList<>();

        mazoCartas.add(new Carta(1, "Corazones", "assets/images/1C.jpg"));
        mazoCartas.add(new Carta(2, "Corazones", "assets/images/2C.jpg"));
        mazoCartas.add(new Carta(3, "Corazones", "assets/images/3C.jpg"));
        mazoCartas.add(new Carta(4, "Corazones", "assets/images/4C.jpg"));
        mazoCartas.add(new Carta(5, "Corazones", "assets/images/5C.jpg"));
        mazoCartas.add(new Carta(6, "Corazones", "assets/images/6C.jpg"));
        mazoCartas.add(new Carta(7, "Corazones", "assets/images/7C.jpg"));
        mazoCartas.add(new Carta(8, "Corazones", "assets/images/8C.jpg"));
        mazoCartas.add(new Carta(9, "Corazones", "assets/images/9C.jpg"));
        mazoCartas.add(new Carta(10, "Corazones", "assets/images/10C.jpg"));
        mazoCartas.add(new Carta(10, "Corazones", "assets/images/11C.jpg"));
        mazoCartas.add(new Carta(10, "Corazones", "assets/images/12C.jpg"));
        mazoCartas.add(new Carta(10, "Corazones", "assets/images/13C.jpg"));
        mazoCartas.add(new Carta(1, "Diamantes", "assets/images/1D.jpg"));
        mazoCartas.add(new Carta(2, "Diamantes", "assets/images/2D.jpg"));
        mazoCartas.add(new Carta(3, "Diamantes", "assets/images/3D.jpg"));
        mazoCartas.add(new Carta(4, "Diamantes", "assets/images/4D.jpg"));
        mazoCartas.add(new Carta(5, "Diamantes", "assets/images/5D.jpg"));
        mazoCartas.add(new Carta(6, "Diamantes", "assets/images/6D.jpg"));
        mazoCartas.add(new Carta(7, "Diamantes", "assets/images/7D.jpg"));
        mazoCartas.add(new Carta(8, "Diamantes", "assets/images/8D.jpg"));
        mazoCartas.add(new Carta(9, "Diamantes", "assets/images/9D.jpg"));
        mazoCartas.add(new Carta(10, "Diamantes", "assets/images/10D.jpg"));
        mazoCartas.add(new Carta(10, "Diamantes", "assets/images/11D.jpg"));
        mazoCartas.add(new Carta(10, "Diamantes", "assets/images/12D.jpg"));
        mazoCartas.add(new Carta(10, "Diamantes", "assets/images/13D.jpg"));
        mazoCartas.add(new Carta(1, "Espadas", "assets/images/1E.jpg"));
        mazoCartas.add(new Carta(2, "Espadas", "assets/images/2E.jpg"));
        mazoCartas.add(new Carta(3, "Espadas", "assets/images/3E.jpg"));
        mazoCartas.add(new Carta(4, "Espadas", "assets/images/4E.jpg"));
        mazoCartas.add(new Carta(5, "Espadas", "assets/images/5E.jpg"));
        mazoCartas.add(new Carta(6, "Espadas", "assets/images/6E.jpg"));
        mazoCartas.add(new Carta(7, "Espadas", "assets/images/7E.jpg"));
        mazoCartas.add(new Carta(8, "Espadas", "assets/images/8E.jpg"));
        mazoCartas.add(new Carta(9, "Espadas", "assets/images/9E.jpg"));
        mazoCartas.add(new Carta(10, "Espadas", "assets/images/10E.jpg"));
        mazoCartas.add(new Carta(10, "Espadas", "assets/images/11E.jpg"));
        mazoCartas.add(new Carta(10, "Espadas", "assets/images/12E.jpg"));
        mazoCartas.add(new Carta(10, "Espadas", "assets/images/13E.jpg"));
        mazoCartas.add(new Carta(1, "Tréboles", "assets/images/1T.jpg"));
        mazoCartas.add(new Carta(2, "Tréboles", "assets/images/2T.jpg"));
        mazoCartas.add(new Carta(3, "Tréboles", "assets/images/3T.jpg"));
        mazoCartas.add(new Carta(4, "Tréboles", "assets/images/4T.jpg"));
        mazoCartas.add(new Carta(5, "Tréboles", "assets/images/5T.jpg"));
        mazoCartas.add(new Carta(6, "Tréboles", "assets/images/6T.jpg"));
        mazoCartas.add(new Carta(7, "Tréboles", "assets/images/7T.jpg"));
        mazoCartas.add(new Carta(8, "Tréboles", "assets/images/8T.jpg"));
        mazoCartas.add(new Carta(9, "Tréboles", "assets/images/9T.jpg"));
        mazoCartas.add(new Carta(10, "Tréboles", "assets/images/10T.jpg"));
        mazoCartas.add(new Carta(10, "Tréboles", "assets/images/11T.jpg"));
        mazoCartas.add(new Carta(10, "Tréboles", "assets/images/12T.jpg"));
        mazoCartas.add(new Carta(10, "Tréboles", "assets/images/13T.jpg"));
    }

    @GetMapping("/test")
    public boolean test(){
        return true;
    }

    @GetMapping("/{mazo}")
    public ArrayList<Carta> test(@PathVariable int mazo) {
        if (mazo == 1){
            return mazoJugador;
        } else {
            return mazoCrupier;
        }
    }



    @GetMapping("/cartas/{tipoJugador}")
    public Carta obtenerCarta(@PathVariable int tipoJugador) {
        Carta cartaSeleccionada = mazoCartas.get((int) (Math.random() * mazoCartas.size()));


        if (tipoJugador == 1) {
            if (mazoJugador.contains(cartaSeleccionada)) {
                cartaSeleccionada = mazoCartas.get((int) (Math.random() * mazoCartas.size()));
            } else {
                mazoJugador.add(cartaSeleccionada);
            }
        } else {
            if (mazoCrupier.contains(cartaSeleccionada)) {
                cartaSeleccionada = mazoCartas.get((int) (Math.random() * mazoCartas.size()));
            } else {
                mazoCrupier.add(cartaSeleccionada);
            }
        }

        return cartaSeleccionada;
    }

    @GetMapping("/cartas/{tipoJugador}/puntos")
    public int calcularPuntos(@PathVariable int tipoJugador) {
        int puntos = 0;
        if (tipoJugador == 1) {
            for (Carta carta : mazoJugador) {
                puntos += carta.getValor();
            }
        } else {
            for (Carta carta : mazoCrupier) {
                puntos += carta.getValor();
            }
        }
        return puntos;
    }

    @GetMapping("/logicaJugador")
    public boolean logicaJugador() {
        int puntos = calcularPuntos(1);
        if (puntos > 21) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/logicaCrupier")
    public boolean logicaCrupier() {
        int puntos = calcularPuntos(2);
        if (puntos < 17) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/reset")
    public boolean reiniciar() {
        mazoJugadorAnterior = mazoJugador;
        mazoCrupierAnterior = mazoCrupier;
        mazoJugador.clear();
        mazoCrupier.clear();

        if (mazoJugador.isEmpty() && mazoCrupier.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/ganador")
    public int getGanador() {
        int puntosJugador = calcularPuntos(1);
        int puntosCrupier = calcularPuntos(2);

        if (puntosJugador > 21) {
            return 2;
        } else if (puntosCrupier > 21) {
            return 1;
        } else if (puntosJugador > puntosCrupier) {
            return 1;
        } else if (puntosJugador < puntosCrupier) {
            return 2;
        } else {
            return 0;
        }
    }

    //make a function to save the game in database
    @GetMapping("/guardar/{id}")
    public void guardarJuego(@PathVariable int id) {

        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blackjack", "root", "123456");

            //Borrado de los mazos viejos
            //mazo jugador
            PreparedStatement delete = conn.prepareStatement("DELETE FROM mazojugador WHERE id_jugador = ?");
            delete.setInt(1, id);
            delete.executeUpdate();

            delete = conn.prepareStatement("DELETE FROM mazojugador WHERE id = ?");
            delete.setInt(1, id);
            delete.executeUpdate();

            //Guardar el mazo del jugador
            PreparedStatement st = conn.prepareStatement("INSERT INTO mazojugador (valor, naipe, imagenURL) VALUES (?, ?,?)");

            for (Carta carta : mazoJugador) {
                st.setInt(1, carta.getValor());
                st.setString(2, carta.getNaipe());
                st.setString(3, carta.getImagenUrl());
                st.executeUpdate();
            }

            st = conn.prepareStatement("INSERT INTO mazocrupier (valor, naipe, imagenURL) VALUES (?, ?,?)");

            for (Carta carta : mazoCrupier) {
                st.setInt(1, carta.getValor());
                st.setString(2, carta.getNaipe());
                st.setString(3, carta.getImagenUrl());
                st.executeUpdate();
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    //make a function to load the game from database
    @GetMapping("/cargarjugador/{id}")
    public ArrayList<Carta> cargarMazoJugador(@PathVariable int id) {
        ArrayList<Carta> mazo = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blackjack", "root", "123456");

            PreparedStatement st = conn.prepareStatement("SELECT * FROM mazojugador WHERE id_jugador = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                mazoJugador.add(new Carta(rs.getInt("valor"), rs.getString("naipe"), rs.getString("imagenURL")));
                mazo.add(new Carta(rs.getInt("valor"), rs.getString("naipe"), rs.getString("imagenURL")));
            }
            return mazo;


        } catch (Exception exc) {
            return null;
        }
    }


    @GetMapping("/cargarcrupier/{id}")
    public ArrayList<Carta> cargarMazoCrupier(@PathVariable int id) {
        ArrayList<Carta> mazo = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blackjack", "root", "123456");

            PreparedStatement st = conn.prepareStatement("SELECT * FROM mazocrupier WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                mazoCrupier.add(new Carta(rs.getInt("valor"), rs.getString("naipe"), rs.getString("imagenURL")));
                mazo.add(new Carta(rs.getInt("valor"), rs.getString("naipe"), rs.getString("imagenURL")));
            }
            return mazo;


        } catch (Exception exc) {
            return null;
        }
    }


    @GetMapping("/hayJuegoGuardado/{id}")
    public boolean hayJuegoGuardado(@PathVariable int id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blackjack", "root", "123456");
            PreparedStatement st = conn.prepareStatement("SELECT * FROM mazojugador WHERE id_jugador = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception exc) {
            return false;
        }
    }





}
