package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextoPuntaje extends JLabel {
    private int y =400;
    private int x=300;
    private double randomX;
    private int aleatorio;
    private int opacidad=200;
    private Timer tiempo;
    private double randomColor;
    private  int roundedColor;
    private Timer tiempoB;
    public  TextoPuntaje(){
        randomX=Math.random()*300;
        aleatorio=(int)Math.round(randomX);
        randomColor=Math.random()*3;
        roundedColor=(int)Math.round(randomColor);
        this.setVisible(true);
        this.setText("+50 puntos");
        //84:d8:1b:d1:d7:8f
        this.setFont(new Font("Arial",1,20));
        this.setBounds(100+aleatorio,y-=2,300,30);
        tiempo= new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (opacidad>=10){
                    opacidad-=10;
                    setLocation(100+aleatorio,y-=1);
                    switch (roundedColor){
                        case 1:
                            setForeground(new Color(147, 251, 22,opacidad));
                            break;
                        case 2:
                            setForeground(new Color(255, 36, 11 ,opacidad));
                            break;
                        case 3:
                            setForeground(new Color(11, 199, 255 ,opacidad));
                            break;
                        case 4:
                            setForeground(new Color(190, 34, 254,opacidad));
                            break;
                        default:
                            setForeground(new Color(34, 254, 124 ,opacidad));
                            break;
                    }
                }else{
                    tiempo.stop();
                }
            }
        });
        tiempo.start();

    }
    public  TextoPuntaje(String nivel){
        this.setVisible(true);
        this.setText(nivel);
        //84:d8:1b:d1:d7:8f
        this.setFont(new Font("Arial",1,30));
        this.setBounds(250,400,300,30);
        tiempoB= new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opacidad-=2;
                if(opacidad>=2){
                    setForeground(new Color(251, 254, 34,opacidad));
                }else{
                    tiempoB.stop();
                }
            }
        });
        tiempoB.start();

    }
}
