package clases;

import bd.Consulta;

public class Principal {
    public static void main(String[] args) {
        VistaUsuario usuario = new VistaUsuario(null,true);
        Sonido s = new Sonido();
        s.audioPrincipal();
        usuario.setVisible(true);
    }
}
