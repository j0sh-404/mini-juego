package bd;

public class DatosPuntaje {
    private int contador;
    private  String nombreJugador;
    private String fecha;
    private int puntaje;

    public DatosPuntaje(int contador, String nombreJugador, String fecha, int puntaje) {
        this.contador = contador;
        this.nombreJugador = nombreJugador;
        this.fecha = fecha;
        this.puntaje = puntaje;
    }

    public int getContador() {
        return contador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public String getFecha() {
        return fecha;
    }

    public int getPuntaje() {
        return puntaje;
    }
}
