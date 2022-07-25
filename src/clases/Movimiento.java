package clases;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movimiento {
    private Timer tiempoDerecha;
    private Timer tiempoIzquierda;
    private final int tiempo=10;
    private boolean derecha=false;
    private boolean izquierda=false;
    public static boolean reiniciar=false;
    int i=1;
    public Movimiento(Vistas vista){
        vista.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(derecha==false){
                    MovimientoPelota.derecha=true;
                if (e.getKeyCode()==39){
                      //limite 486
                     tiempoDerecha= new Timer(tiempo,new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            vista.movimientoXPos();
                            if (vista.posicionX()>=486){
                                tiempoDerecha.stop();
                                izquierda=false;
                            }
                        }
                    });
                         if (izquierda){
                             tiempoIzquierda.stop();
                         }
                         tiempoDerecha.start();
                         derecha=true;
                         izquierda=false;
                     }
                } if(e.getKeyCode()==37){
                    //limite -4
                    if(izquierda==false){
                        MovimientoPelota.derecha=false;
                    tiempoIzquierda= new Timer(tiempo,new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            vista.movimientoXNeg();
                            if (vista.posicionX()<=-4){
                                tiempoIzquierda.stop();
                                derecha=false;
                            }

                        }
                    });
                        if(derecha){
                            tiempoDerecha.stop();
                        }
                        tiempoIzquierda.start();
                        izquierda=true;
                        derecha=false;
                    }

                }
                   /*if (reiniciar){
                       vista.i();
                       reiniciar=false;
                   }
                  if (e.getKeyChar()=='v'){
                      vista.mostrarMensaje();
                  }*/
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        VistaPerdedor.setVista(vista);
    }

}
