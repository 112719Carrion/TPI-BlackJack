package tpi.backend.Models;

import java.util.ArrayList;

public class Carta {


    private int valor;
    private String naipe;
    private String imagenUrl;


    public Carta(int valor, String naipe, String imagenUrl) {
        this.valor = valor;
        this.naipe = naipe;
        this.imagenUrl = imagenUrl;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getNaipe() {
        return naipe;
    }

    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
