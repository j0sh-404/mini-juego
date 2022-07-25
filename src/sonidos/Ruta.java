package sonidos;

public interface Ruta {
    public static  String obtenerRutaAudioFondo(){
        return Ruta.class.getResource("Principal.mp3").getPath();
    }
    public static  String obtenerRutaAudioRebote(){
        return Ruta.class.getResource("rebotePelota.mp3").getPath();
    }
    public static  String obtenerRutaAudioChillido(){
        return Ruta.class.getResource("chillido.mp3").getPath();
    }
    public static  String obtenerRutaAudioBoton(){
        return Ruta.class.getResource("boton.mp3").getPath();
    }
    public static  String obtenerRutaAudioTeclado(){
        return Ruta.class.getResource("SonidoTeclado.mp3").getPath();
    }
}
