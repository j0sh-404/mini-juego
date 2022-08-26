package clases;

import java.io.File;


import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sonidos.Ruta;

import java.io.File;

public class Sonido {
    final JFXPanel javaFx = new JFXPanel();
    private static MediaPlayer audioFondo;
    private static MediaPlayer audioRebote;
    private static MediaPlayer audioChillido;
    private static MediaPlayer audioBoton;
    private static MediaPlayer audioTeclado;
    public void audioPrincipal() {
        String rutaAudioFondo = Ruta.obtenerRutaAudioFondo();
        Media url = new Media(new File(rutaAudioFondo).toURI().toString());
        audioFondo = new MediaPlayer(url);
        audioFondo.setAutoPlay(true);
        audioFondo.setCycleCount(MediaPlayer.INDEFINITE);
    }
    public void audioRebote() {
        String rutaAudioFondo = Ruta.obtenerRutaAudioRebote();
        Media url = new Media(new File(rutaAudioFondo).toURI().toString());
        audioRebote = new MediaPlayer(url);
        audioRebote.play();
    }
    public void audioChillido() {
        String rutaAudioFondo = Ruta.obtenerRutaAudioChillido();
        Media url = new Media(new File(rutaAudioFondo).toURI().toString());
        audioChillido = new MediaPlayer(url);
        audioChillido.play();
    }

    public static void audioBoton() {
        String rutaAudioFondo = Ruta.obtenerRutaAudioBoton();
        Media url = new Media(new File(rutaAudioFondo).toURI().toString());
        audioBoton= new MediaPlayer(url);
        audioBoton.play();
    }
    public static void audioTeclado() {
        String rutaAudioFondo = Ruta.obtenerRutaAudioTeclado();
        Media url = new Media(new File(rutaAudioFondo).toURI().toString());
        audioTeclado= new MediaPlayer(url);
        audioTeclado.play();
    }
}
