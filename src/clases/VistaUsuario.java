package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VistaUsuario extends JDialog implements ActionListener {
    private JPanel panel;
    private JButton botonGuardar;
    private JButton botonCancelar;
    private JLabel  textoUsuario, textoNombreJuego;
    private JTextField nombreUsuario;
    private boolean  esPrimero=true;
    private Vistas vistas;
    private static Vistas v;
    private VistaPerdedor perdedor = new VistaPerdedor(null,false);
    public VistaUsuario(JFrame jframe, boolean modal ) {
        super(jframe, modal);
        this.setSize(600,400);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        implementarElementos();
        nombreUsuario.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
               Sonido.audioTeclado();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
    public void setEsPrimero(boolean esPrimero) {
        this.esPrimero = esPrimero;
    }
    public static void setV(Vistas v) {
        VistaUsuario.v = v;
    }
    public void implementarElementos(){
        panel =new FondoUsuario();
        panel.setBounds(0,0,300,200);
        panel.setLayout(null);
        getContentPane().add(panel);

        textoNombreJuego = new JLabel("Bad Monster");
        textoNombreJuego.setBounds(200,20,300,70);
        textoNombreJuego.setForeground(Color.white);
        textoNombreJuego.setFont(new Font("Almonte Snow",0,50));
        panel.add(textoNombreJuego);

        textoUsuario= new JLabel("Nombre   Jugador:");
        textoUsuario.setBounds(50,200,200,30);
        textoUsuario.setFont(new Font("Distant Galaxy",0,15));
        textoUsuario.setForeground(Color.white);
        panel.add(textoUsuario);

        nombreUsuario=new JTextField();
        nombreUsuario.setBounds(50,240,150,30);
        nombreUsuario.setFont(new Font("arial black",0,10));
        panel.add(nombreUsuario);
        botonGuardar= new JButton();
        botonGuardar.setBounds(10,300,100,30);
        botonGuardar.setText("save");
        panel.add(botonGuardar);

        botonCancelar=new JButton();
        botonCancelar.setBounds(130,300,100,30);
        botonCancelar.setText("cancel");
        panel.add(botonCancelar);

        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validar();
            }
        });

        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (esPrimero){
                System.exit(0);
                }else{
                    dispose();
                    perdedor.setVisible(true);
                }
            }
        });
    }
    private void validar(){
      String nombre= nombreUsuario.getText();
      if (nombre.isEmpty()){
          JOptionPane.showMessageDialog(null,"El campo no puede estar vacio");
      }else{
          if (nombre.length()<4){
              JOptionPane.showMessageDialog(null,"El nombre del jugador debe tener al menos 4 caracteres");
          }else{
              if (esPrimero){
                  vistas= new Vistas();
                  Usuario.setNombreJugador(nombreUsuario.getText());
                  Movimiento movimiento = new Movimiento(vistas);
                  MovimientoPelota movimientoPelota= new MovimientoPelota(vistas);
                  vistas.setVisible(true);
                  this.dispose();
              }else{
                  Usuario.setNombreJugador(nombreUsuario.getText());
                  v.reintentar();
                  dispose();
              }
          }
      }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
class FondoUsuario extends JPanel{
    public void paint(Graphics g){
        Image imagen = new ImageIcon(getClass().getResource("/imagenes/fondoUsuario.jpg")).getImage();
        g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}
