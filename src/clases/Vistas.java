package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Vistas extends JFrame {
    //426 en y la bola cae en la rama
    private JPanel panel  = new Graficos();
    private int largo=600;
    private int ancho=600;
    private int inicioRamaX=250;
    private int movimientoPelotaX=280;
    private int movimientoPelotaY=200;
    private JLabel etiqueta;
    private JLabel etiquetaBola;
    private ImageIcon mounstro1 = new ImageIcon(getClass().getResource("/monsters/monster1.png"));
    private ImageIcon mounstro2 = new ImageIcon(getClass().getResource("/monsters/monster2.png"));
    private ImageIcon mounstro3 = new ImageIcon(getClass().getResource("/monsters/monster3.png"));
    private ImageIcon mounstro  = new ImageIcon(getClass().getResource("/monsters/monster.png"));
    private int xMounstruo=15;
    private int yMounstro=-500;//-500;
    private int filas=10;//10
    private int numeroMounstros=11;
    private Timer tiempoAvanceMounstro;
    private JLabel[] etiquetas = new JLabel[filas*numeroMounstros];
    private PosicionMounstro matrizMounstro[][]= new PosicionMounstro[filas][numeroMounstros];
    private int contador=0;
    private int tiempoMovimiento=10000;
    private Timer t;
    private VistaPerdedor perdedor = new VistaPerdedor(this,true);
    private int opacidad;
    private Timer mostrarTextoDesvanecido;
    private MovimientoPelota movimientoPelota;
    private  static int puntaje=0;
    //poder eliminar
    private int indice=-1;
    private  boolean desvaneciendo=false;
    //aumentar de nivel
    private int contadorMountruoEliminado=0;
    private int nivel=1;
    private Timer tiempoEspera;
    public Vistas(){
        this.setSize(ancho,largo);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("MiniGame");
        this.setLocationRelativeTo(null);
        this.setContentPane(panel);
        this.setUndecorated(true);
        //this.setResizable(false);
        panel.setLayout(null);
        puntoInicial();
        iterando();
        imprimiendo();
    }

    public void setxMounstruo(int xMounstruo) {
        this.xMounstruo = xMounstruo;
    }

    public void setyMounstro(int yMounstro) {
        this.yMounstro = yMounstro;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }


    public static int getPuntaje() {
        return puntaje;
    }

    public void detenerT() {
        this.t.stop();
    }

    public void reintentar(){
        nivel=0;
        contadorMountruoEliminado=0;
        puntaje=0;
        xMounstruo=15;
        yMounstro=-500;
        movimientoPelotaX=280;
        movimientoPelotaY=200;
        inicioRamaX=250;
        etiquetaBola.setLocation(movimientoPelotaX,movimientoPelotaY);
        etiqueta.setLocation(inicioRamaX,450);
        tiempoMovimiento=10000;
        System.out.println(tiempoMovimiento);
        contador=0;
        int max =etiquetas.length;
        for (int i =max-1;i>=0;i--){
            etiquetas[i].setVisible(false);
        }
        iterando();
        imprimiendo();
        movimientoPelota= new MovimientoPelota(this);
        t.start();
    }
    private void puntoInicial(){
        etiqueta = new JLabel();
        etiqueta.setBounds(inicioRamaX,450,100,35);
        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/ramita.png"));
        etiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(etiqueta.getWidth(),etiqueta.getHeight(),Image.SCALE_SMOOTH)));
        panel.add(etiqueta);

        etiquetaBola = new JLabel();
        ImageIcon imagenBola = new ImageIcon(getClass().getResource("/pelotas/bola.gif"));
        etiquetaBola.setBounds(movimientoPelotaX,movimientoPelotaY,70,70);
        etiquetaBola.setIcon(new ImageIcon(imagenBola.getImage().getScaledInstance(etiquetaBola.getWidth(),etiquetaBola.getHeight(),Image.SCALE_FAST)));
        panel.add(etiquetaBola);
    }
    public JLabel[] getEtiquetas() {
        return etiquetas;
    }
    public void rebotePelotaX(int decrementoX, int decrementoY, boolean derecha, boolean caida){
        if (caida){
                if (derecha){
                    etiquetaBola.setLocation(movimientoPelotaX-=decrementoX,movimientoPelotaY+=decrementoY);
                }else{
                    etiquetaBola.setLocation(movimientoPelotaX+=decrementoX,movimientoPelotaY+=decrementoY);
                }
        }else{
            if (derecha){
                etiquetaBola.setLocation(movimientoPelotaX+=decrementoX,movimientoPelotaY-=decrementoY);
            }else{
                etiquetaBola.setLocation(movimientoPelotaX-=decrementoX,movimientoPelotaY-=decrementoY);
            }

        }
    }
    public void movimientoXPos(){
        etiqueta.setLocation(inicioRamaX+=6,450);

    }
    public void  movimientoXNeg(){
        etiqueta.setLocation(inicioRamaX-=6,450);

    }
    public int posicionX(){
        return inicioRamaX;
    }
    public void movimientoPelotaY(){
      etiquetaBola.setLocation(movimientoPelotaX,movimientoPelotaY+=1);
    }
    public int getMovimientoPelotaX() {
        return movimientoPelotaX;
    }
    public int getMovimientoPelotaY() {
        return movimientoPelotaY;
    }
    public void iterando(){
        for ( int i =0;i<matrizMounstro.length;i++){
            for (int j =0;j<matrizMounstro[i].length;j++){
                double aleatorio = Math.random()*3;
                int numero =(int)Math.round(aleatorio);
                matrizMounstro[i][j]= new PosicionMounstro(xMounstruo,yMounstro,numero);
                xMounstruo+=50;
                etiquetas[contador++]=new JLabel();
            }
            xMounstruo=15;
            yMounstro+=60;
        }
        moviendo();
    }
    public void  imprimiendo(){
        contador=0;
        for ( int i =0;i<matrizMounstro.length;i++){
            for (int j=0;j<matrizMounstro[i].length;j++){
                etiquetas[contador].setBounds(matrizMounstro[i][j].getPosicionX(),matrizMounstro[i][j].getPosicionY(),50,50);
                switch (matrizMounstro[i][j].getTipoMounstro()){
                    case 1:
                        etiquetas[contador].setIcon(new ImageIcon(mounstro.getImage().getScaledInstance(etiquetas[contador].getWidth(),etiquetas[contador].getHeight(), Image.SCALE_SMOOTH)));
                        break;
                    case 2:
                        etiquetas[contador].setIcon(new ImageIcon(mounstro1.getImage().getScaledInstance(etiquetas[contador].getWidth(),etiquetas[contador].getHeight(), Image.SCALE_SMOOTH)));
                        break;
                    case 3:
                        etiquetas[contador].setIcon(new ImageIcon(mounstro2.getImage().getScaledInstance(etiquetas[contador].getWidth(),etiquetas[contador].getHeight(), Image.SCALE_SMOOTH)));
                        break;
                    default:
                        etiquetas[contador].setIcon(new ImageIcon(mounstro3.getImage().getScaledInstance(etiquetas[contador].getWidth(),etiquetas[contador].getHeight(), Image.SCALE_SMOOTH)));
                        break;
                }
                panel.add(etiquetas[contador]);
                contador++;
                panel.repaint();
            }
        }
    }
    private void  moviendo(){
        t = new Timer(tiempoMovimiento, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i =etiquetas.length-1;i>=0;i--){
                   int x=etiquetas[i].getX();
                   int y=etiquetas[i].getY();
                   etiquetas[i].setLocation(x,y+=10);
                   if (etiquetas[i].getY()>=450 && etiquetas[i].isVisible()){
                       t.stop();
                       perdedor.setVisible(true);
                   }
                }

            }
        });
        t.start();
    }
    public void eliminarMounstruo(int indice) {
        panel.add(new EfectoFuego(etiquetas[indice].getX(),etiquetas[indice].getY()));
        etiquetas[indice].setLocation(-80, 560);
        etiquetas[indice].setVisible(false);
        contadorMountruoEliminado++;
        if (contadorMountruoEliminado==etiquetas.length){
           if (tiempoMovimiento>1000){
               nivel++;
               panel.add(new TextoPuntaje("Nivel "+nivel));
               t.stop();
               tiempoEspera= new Timer(500, new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       yMounstro=-500;
                       xMounstruo=15;
                       movimientoPelotaX=280;
                       movimientoPelotaY=150;
                       int max =etiquetas.length;
                       for (int i =max-1;i>=0;i--){
                           etiquetas[i].setVisible(false);
                       }
                       tiempoMovimiento-=1000;
                       contador=0;
                       iterando();
                       imprimiendo();
                       contadorMountruoEliminado=0;
                       tiempoMovimiento-=1000;
                       tiempoEspera.stop();
                   }
               });
              tiempoEspera.start();
           }
        }
    }
    public void mostrarMensaje(){
     panel.add(new TextoPuntaje());
     puntaje+=50;
    }

}

