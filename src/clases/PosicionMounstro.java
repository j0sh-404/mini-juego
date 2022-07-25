package clases;

import javax.swing.*;
import java.awt.*;

public class PosicionMounstro {
    private int posicionX;
    private int posicionY;
    private int tipoMounstro;

    public PosicionMounstro(int posicionX, int posicionY, int tipoMounstro) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.tipoMounstro = tipoMounstro;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getTipoMounstro() {
        return tipoMounstro;
    }

    public void setTipoMounstro(int tipoMounstro) {
        this.tipoMounstro = tipoMounstro;
    }


    @Override
    public String toString() {
        return "PosicionMounstro{" +
                "posicionX=" + posicionX +
                ", posicionY=" + posicionY +
                ", tipoMounstro=" + tipoMounstro +
                '}';
    }
}
