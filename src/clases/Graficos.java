package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Graficos extends JPanel {
    //sitio web de musica https://www.newgrounds.com/audio/listen/1063120

    @Override
    public void paint(Graphics g){
        Image imagen= new ImageIcon(getClass().getResource("/imagenes/fondo2.jpg")).getImage();
        g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }

}




