package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EfectoFuego extends JLabel{
    ImageIcon imagen =new ImageIcon(getClass().getResource("/imagenes/efectoFuego.gif"));
    Timer tiempo;
    int  desvanecer=40;
    Timer tiempoEspera;
    public EfectoFuego(int x, int y) {
        System.out.println(x+"-y-"+y);
        this.setVisible(true);
        this.setLocation(x,y+4);
        this.setSize(40,40);
        this.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_FAST)));
        tiempo= new Timer(40,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desvanecer-=2;
                if (desvanecer>=0){
                    setSize(40,desvanecer);
                }else{
                    setVisible(false);
                    tiempo.stop();
                }

            }
        });
      tiempo.start();

    }

}

