package clases;

import bd.Consulta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovimientoPelota {
    private Timer caida;
    private Vistas vista;
    private VistaPerdedor vistaPerdedor;
    public static boolean derecha;
    private boolean esCaida=true;
    private int avanceX=0;
    //Variables para realizar el movimiento
    private boolean esDerecha;
    private Timer movimientoDerechaAIzquierda;
    private Timer movimientoIzquierdaADerecha;
    //tiempo caida
    private Timer movimientoIzquierdaAderechaCaida;
    private Timer movimientoDerechaAIzquierdaCaida;
    private boolean perdedor=false;
    private final int anchoYLargo=50;
    //Sonidos
    private Sonido s = new Sonido();
    //Eliminar este elemento
    private Consulta consulta = new Consulta();
    Usuario usuario = new Usuario();
    private int nivel=0;
    public MovimientoPelota(Vistas vista){
     //La pelota realiza el primer movimiento cayendo
     avanceX=numeroAleatorio();
     caida = new Timer(3,new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             if (vista.getMovimientoPelotaY()>=424 && vista.getMovimientoPelotaY()<=460 && vista.getMovimientoPelotaX()>=vista.posicionX() && vista.getMovimientoPelotaX()<=vista.posicionX()+88){
                 caida.stop();
                 movimientoArriba();
                 esCaida=false;
             }//fin de la condicion
             if (vista.getMovimientoPelotaY()>600){
                 caida.stop();
                 vistaPerdedor.setVisible(true);
                 perdedor =false;
             }
             vista.movimientoPelotaY();
         }
     });
     caida.start();
     this.vista=vista;
     vistaPerdedor = new VistaPerdedor(vista,true);
 }

    public Timer getCaida() {
        return caida;
    }


    private void movimientoArriba(){
     if (derecha){
         movimientoDerechaAIzquierda();
     }else{
         movimientoIzquierdaADerecha();
     }

}
    private void movimientoDerechaAIzquierda(){
     esDerecha=true;
     int x= numeroAleatorio();
     movimientoDerechaAIzquierda= new Timer(10, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             if (esCaida==false && esDerecha==true){
                 //La pelota va hacia la derecha y para arriba
                 vista.rebotePelotaX(x,2,esDerecha,esCaida);
             } if (esCaida==false && esDerecha==true && vista.getMovimientoPelotaX()>=545){
                 movimientoDerechaAIzquierda.stop();
                 s.audioRebote();
                 movimientoIzquierdaADerecha();
             } if (esCaida==false && esDerecha==true && vista.getMovimientoPelotaY()<=2){
                 esCaida=true;
                 movimientoDerechaAIzquierda.stop();
                 s.audioRebote();
                 movimientoDerechaAIzquierdaCaida();
             }
             //caida
             obtenerCoordenadasMounstro();
         }
     });
 if ( esDerecha){
     movimientoDerechaAIzquierda.start();
     try{
         movimientoIzquierdaADerecha.stop();
         movimientoDerechaAIzquierdaCaida.stop();
         movimientoIzquierdaAderechaCaida.stop();
     }catch (NullPointerException e){

     }
 }
}
    private void movimientoIzquierdaADerecha(){
     esDerecha=false;
     int x = numeroAleatorio();
     movimientoIzquierdaADerecha= new Timer(10, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             if (esCaida==false && esDerecha==false){
                 //La pelota va hacia la izquierda y para arriba
                 vista.rebotePelotaX(x,2,esDerecha,esCaida);
             } if (esCaida==false && esDerecha==false && vista.getMovimientoPelotaX()<=2){
                 //La pelota rebota en el lado izquierdo y se dirige hacia la derecha
                 movimientoIzquierdaADerecha.stop();
                 s.audioRebote();
                 movimientoDerechaAIzquierda();
             }  if (esCaida==false && esDerecha==false && vista.getMovimientoPelotaY()<=2){
                 esCaida=true;
                 movimientoIzquierdaADerecha.stop();
                 s.audioRebote();
                 movimientoIzquierdaADerechaCaida();
             }
             //caida
             obtenerCoordenadasMounstro();
         }
     });
     if (esDerecha==false){
         movimientoIzquierdaADerecha.start();
         try{
             movimientoDerechaAIzquierda.stop();
             movimientoDerechaAIzquierdaCaida.stop();
             movimientoIzquierdaAderechaCaida.stop();
         }catch (NullPointerException e){

         }
     }
}
    private void obtenerCoordenadasMounstro(){
     int maximo=vista.getEtiquetas().length-1;
     for (int i=maximo;i>=0;i--){
        if (vista.getMovimientoPelotaX()>=vista.getEtiquetas()[i].getX() && vista.getMovimientoPelotaX()<=vista.getEtiquetas()[i].getX()+anchoYLargo
                && vista.getMovimientoPelotaY()-10<=vista.getEtiquetas()[i].getY() && vista.getMovimientoPelotaY()>=vista.getEtiquetas()[i].getY()-anchoYLargo
                || vista.getMovimientoPelotaX()<=vista.getEtiquetas()[i].getX()&& vista.getMovimientoPelotaX()>=vista.getEtiquetas()[i].getX()-anchoYLargo
                && vista.getMovimientoPelotaY()+10>=vista.getEtiquetas()[i].getY() && vista.getMovimientoPelotaY()<=vista.getEtiquetas()[i].getY()+anchoYLargo
        ){
                           s.audioChillido();
                           vista.mostrarMensaje();

                         if (esDerecha){
                             movimientoDerechaAIzquierda.stop();
                             esCaida=true;
                             movimientoDerechaAIzquierdaCaida();
                         }else{
                             movimientoIzquierdaADerecha.stop();
                             esCaida=true;
                             movimientoIzquierdaADerechaCaida();
                         }
            vista.eliminarMounstruo(i);
        }
     }
 }
    private void movimientoIzquierdaADerechaCaida(){
        esDerecha=true;
        int x = numeroAleatorio();
        int y =2;
        movimientoIzquierdaAderechaCaida= new Timer(13, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (esCaida==true && esDerecha==true){
                    //La pelota va hacia la izquierda y para arriba
                    vista.rebotePelotaX(x,y,esDerecha,esCaida);
                }if (esCaida==true && esDerecha==true && vista.getMovimientoPelotaX()<=2){
                    //La pelota rebota en el lado izquierdo y se dirige hacia la derecha
                    movimientoIzquierdaAderechaCaida.stop();
                    s.audioRebote();
                    movimientoDerechaAIzquierdaCaida();
                }
                if (vista.getMovimientoPelotaY()>=424 && vista.getMovimientoPelotaY()<=460 && vista.getMovimientoPelotaX()>=vista.posicionX() && vista.getMovimientoPelotaX()<=vista.posicionX()+88){
                    movimientoIzquierdaAderechaCaida.stop();
                    esCaida=false;
                    movimientoArriba();
                }
                if (vista.getMovimientoPelotaY()>600){
                    movimientoIzquierdaAderechaCaida.stop();
                    perdedor=true;
                    mostrarVentanaPerdedor();
                }
                obtenerCoordenadasMounstro();
            }
        });
        if (esDerecha){
            movimientoIzquierdaAderechaCaida.start();
            try{
                movimientoDerechaAIzquierdaCaida.stop();
                movimientoIzquierdaADerecha.stop();
                movimientoDerechaAIzquierda.stop();
            }catch (NullPointerException e){

            }
        }

    }
    private void movimientoDerechaAIzquierdaCaida(){
     esDerecha=false;
     int x = numeroAleatorio();
     int y =2;
        movimientoDerechaAIzquierdaCaida = new Timer(13, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (esCaida==true && esDerecha==false){
                    //La pelota va hacia la izquierda y para arriba
                    vista.rebotePelotaX(x,y,esDerecha,esCaida);
                } if (esCaida==true && esDerecha==false && vista.getMovimientoPelotaX()>=545){
                    movimientoDerechaAIzquierdaCaida.stop();
                    s.audioRebote();
                    movimientoIzquierdaADerechaCaida();
                }
                if (vista.getMovimientoPelotaY()>=424 && vista.getMovimientoPelotaY()<=460 && vista.getMovimientoPelotaX()>=vista.posicionX() && vista.getMovimientoPelotaX()<=vista.posicionX()+88){
                    movimientoDerechaAIzquierdaCaida.stop();
                    esCaida=false;
                    s.audioRebote();
                    movimientoArriba();
                }
                if (vista.getMovimientoPelotaY()>600){
                    movimientoDerechaAIzquierdaCaida.stop();
                    perdedor=true;
                    mostrarVentanaPerdedor();
                    vista.detenerT();
                }
                obtenerCoordenadasMounstro();

            }
        });
        if (esDerecha==false){
            movimientoDerechaAIzquierdaCaida.start();
            try{
                movimientoIzquierdaAderechaCaida.stop();
                movimientoDerechaAIzquierda.stop();
                movimientoIzquierdaADerecha.stop();
            }catch (NullPointerException e){

            }
        }
    }
    public int numeroAleatorio(){
     double randomX= Math.random()*8;
     int aleatorio=(int)Math.round(randomX);
     return aleatorio;
 }
 public void detenerTodo(){
         movimientoIzquierdaADerecha.stop();
         movimientoDerechaAIzquierda.stop();
         movimientoDerechaAIzquierdaCaida.stop();
         movimientoIzquierdaAderechaCaida.stop();

 }
    private void mostrarVentanaPerdedor(){
     if (perdedor){
         vista.detenerT();
         Date obtenerFecha = new Date();
         SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY,MM,dd");
         consulta.insertarPuntaje(Usuario.getNombreJugador(),formatoFecha.format(obtenerFecha),Vistas.getPuntaje());
         vistaPerdedor.insertarPuntaje("Puntaje: "+Vistas.getPuntaje());
         vistaPerdedor.setVisible(true);
         perdedor=false;
     }
 }

}
