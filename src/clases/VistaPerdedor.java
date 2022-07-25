package clases;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VistaPerdedor extends JDialog implements ActionListener {
    private JPanel panel;
    private JButton botonIntentarNuevamente;
    private JButton botonListar;
    private JButton botonCerrar;
    private JButton botonNuevoJugador;
    public static  Vistas vistas;
    private JLabel mostrarPuntaje;
    private static int puntaje=0;
    private final int lado=80;
    private  VistaPuntaje vistaPuntaje;
    private JLabel textoReiniciar,textoCancelar,textoVerPuntaje,textoNuevoJugador, textoPerdedor;
    public VistaPerdedor(JFrame jframe, boolean modal) {
        super(jframe, modal);
        this.setSize(600, 400);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        panel = new FondoPerdedor();
        panel.setLayout(null);
        panel.setBounds(0, 0, 600, 400);
        this.getContentPane().add(panel);
        VistaUsuario.setV(VistaPerdedor.vistas);
        colocarElementos();
    }

    public static void setPuntaje(int puntaje) {
        VistaPerdedor.puntaje += puntaje;
        System.out.println(VistaPerdedor.puntaje);
    }
    public void insertarPuntaje(String puntajeTotal){
        textoPerdedor = new JLabel("Perdiste");
        textoPerdedor.setBounds(220,100,300,40);
        textoPerdedor.setFont(new Font("Almonte Snow",0,50));
        textoPerdedor.setForeground(Color.white);
        panel.add(textoPerdedor);

        mostrarPuntaje = new JLabel();
        mostrarPuntaje.setBounds(20,360,200,30);
        mostrarPuntaje.setText(puntajeTotal);
        mostrarPuntaje.setForeground(Color.white);
        mostrarPuntaje.setFont(new Font("Distant Galaxy",0,15));
        panel.add(mostrarPuntaje);
    }

    public static void setVista(Vistas vista) {
        vistas = vista;
    }

    public static Vistas getVistas() {
        return vistas;
    }

    private void colocarElementos(){
         ImageIcon imagen= new ImageIcon(getClass().getResource("/imagenes/reiniciar.png"));
         botonIntentarNuevamente= new JButton();
         botonIntentarNuevamente.setBounds(120,220,lado,lado);
         botonIntentarNuevamente.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(botonIntentarNuevamente.getWidth(),botonIntentarNuevamente.getHeight(), Image.SCALE_SMOOTH)));
         botonIntentarNuevamente.setIconTextGap(0);
         botonIntentarNuevamente.setBorderPainted(false);
         botonIntentarNuevamente.setBorder(null);
         botonIntentarNuevamente.setFocusPainted(false);
         botonIntentarNuevamente.setContentAreaFilled(false);
         panel.add(botonIntentarNuevamente);
         textoReiniciar= new JLabel("Intentar Nuevamente");
         textoReiniciar.setBounds(115,320,200,30);
         textoReiniciar.setForeground(Color.white);
         textoReiniciar.setVisible(false);
         panel.add(textoReiniciar);
         botonIntentarNuevamente.addMouseListener(new MouseListener() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 vistas.reintentar();
                 ocultar();
             }

             @Override
             public void mousePressed(MouseEvent e) {

             }

             @Override
             public void mouseReleased(MouseEvent e) {

             }

             @Override
             public void mouseEntered(MouseEvent e) {
                 Sonido.audioBoton();
                 botonIntentarNuevamente.setSize(lado+30,lado+30);
                 botonIntentarNuevamente.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(botonIntentarNuevamente.getWidth(),botonIntentarNuevamente.getHeight(), Image.SCALE_SMOOTH)));
                 textoReiniciar.setVisible(true);
             }
             @Override
             public void mouseExited(MouseEvent e) {
             botonIntentarNuevamente.setSize(lado,lado);
             botonIntentarNuevamente.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(botonIntentarNuevamente.getWidth(),botonIntentarNuevamente.getHeight(), Image.SCALE_SMOOTH)));
             textoReiniciar.setVisible(false);
             }
         });

        ImageIcon imagenListar= new ImageIcon(getClass().getResource("/imagenes/botonListado.png"));
        botonListar= new JButton();
        botonListar.setBounds(220,220,lado,lado);
        botonListar.setMargin(new Insets(0,0,0,0));
        botonListar.setIcon(new ImageIcon(imagenListar.getImage().getScaledInstance(botonListar.getWidth(),botonListar.getHeight(), Image.SCALE_SMOOTH)));
        botonListar.setIconTextGap(0);
        botonListar.setBorderPainted(false);
        botonListar.setBorder(null);
        botonListar.setFocusPainted(false);
        botonListar.setContentAreaFilled(false);
        panel.add(botonListar);
        botonListar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vistaPuntaje= new VistaPuntaje(vistas,true);
                vistaPuntaje.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Sonido.audioBoton();
                botonListar.setSize(lado+30,lado+30);
                botonListar.setIcon(new ImageIcon(imagenListar.getImage().getScaledInstance(botonListar.getWidth(),botonListar.getHeight(), Image.SCALE_SMOOTH)));
                textoVerPuntaje.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonListar.setSize(lado,lado);
                botonListar.setIcon(new ImageIcon(imagenListar.getImage().getScaledInstance(botonListar.getWidth(),botonListar.getHeight(), Image.SCALE_SMOOTH)));
                textoVerPuntaje.setVisible(false);
            }
        });
        textoVerPuntaje = new JLabel("Puntaje");
        textoVerPuntaje.setBounds(253,320,200,30);
        textoVerPuntaje.setVisible(false);
        textoVerPuntaje.setForeground(Color.white);
        panel.add(textoVerPuntaje);

        ImageIcon imagenNuevoJugador= new ImageIcon(getClass().getResource("/imagenes/nuevoJugador.png"));
        botonNuevoJugador= new JButton();
        botonNuevoJugador.setBounds(310,220,lado,lado);
        botonNuevoJugador.setIcon(new ImageIcon(imagenNuevoJugador.getImage().getScaledInstance(botonListar.getWidth(),botonListar.getHeight(), Image.SCALE_SMOOTH)));
        botonNuevoJugador.setIconTextGap(0);
        botonNuevoJugador.setBorderPainted(false);
        botonNuevoJugador.setBorder(null);
        botonNuevoJugador.setFocusPainted(false);
        botonNuevoJugador.setContentAreaFilled(false);
        panel.add(botonNuevoJugador);
        textoNuevoJugador = new JLabel("Nuevo jugador");
        textoNuevoJugador.setBounds(325,320,200,30);
        textoNuevoJugador.setForeground(Color.white);
        textoNuevoJugador.setVisible(false);
        textoNuevoJugador.setForeground(Color.white);
        panel.add(textoNuevoJugador);
        botonNuevoJugador.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
             VistaUsuario v= new VistaUsuario(null,true);
                dispose();
             v.setEsPrimero(false);
             v.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Sonido.audioBoton();
                botonNuevoJugador.setSize(lado+30,lado+30);
                botonNuevoJugador.setIcon(new ImageIcon(imagenNuevoJugador.getImage().getScaledInstance(botonNuevoJugador.getWidth(),botonNuevoJugador.getHeight(), Image.SCALE_SMOOTH)));
                textoNuevoJugador.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonNuevoJugador.setSize(lado,lado);
                botonNuevoJugador.setIcon(new ImageIcon(imagenNuevoJugador.getImage().getScaledInstance(botonNuevoJugador.getWidth(),botonNuevoJugador.getHeight(), Image.SCALE_SMOOTH)));
                textoNuevoJugador.setVisible(false);
            }
        });

        ImageIcon imagenCerrar= new ImageIcon(getClass().getResource("/imagenes/close.png"));
        botonCerrar= new JButton();
        botonCerrar.setBounds(400,220,lado,lado);
        botonCerrar.setIcon(new ImageIcon(imagenCerrar.getImage().getScaledInstance(botonListar.getWidth(),botonListar.getHeight(), Image.SCALE_SMOOTH)));
        botonCerrar.setIconTextGap(0);
        botonCerrar.setBorderPainted(false);
        botonCerrar.setBorder(null);
        botonCerrar.setFocusPainted(false);
        botonCerrar.setContentAreaFilled(false);
        panel.add(botonCerrar);

        textoCancelar= new JLabel("Salir");
        textoCancelar.setBounds(442,320,200,30);
        textoCancelar.setVisible(false);
        textoCancelar.setForeground(Color.white);
        panel.add(textoCancelar);
        botonCerrar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Sonido.audioBoton();
                botonCerrar.setSize(lado+30,lado+30);
                botonCerrar.setIcon(new ImageIcon(imagenCerrar.getImage().getScaledInstance(botonCerrar.getWidth(),botonCerrar.getHeight(), Image.SCALE_SMOOTH)));
                textoCancelar.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonCerrar.setSize(lado,lado);
                botonCerrar.setIcon(new ImageIcon(imagenCerrar.getImage().getScaledInstance(botonListar.getWidth(),botonListar.getHeight(), Image.SCALE_SMOOTH)));
                textoCancelar.setVisible(false);
            }
        });

     }
    private void ocultar(){
        this.dispose();
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

class FondoPerdedor extends JPanel{
    public void paint(Graphics g){
        Image imagen = new ImageIcon(getClass().getResource("/imagenes/fondoGameOver.jpg")).getImage();
        g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}