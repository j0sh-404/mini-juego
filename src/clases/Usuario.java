package clases;

public class Usuario{
   private static  String nombreJugador;

    public static String getNombreJugador() {
        return nombreJugador;
    }

    public static void setNombreJugador(String nombreJugador) {
        Usuario.nombreJugador = nombreJugador;
    }
}
