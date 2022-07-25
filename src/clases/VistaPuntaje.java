package clases;

import bd.Consulta;
import bd.DatosPuntaje;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VistaPuntaje extends JDialog  implements ActionListener {
    private JPanel panel;
    private JButton botonRegresar;
    private JTable tabla;
    private Consulta consulta= new Consulta();
    private  DefaultTableModel modelo;
    public  VistaPuntaje(JFrame jFrame, boolean modal){
        super(jFrame,modal);
        this.setSize(400,400);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        consulta.obtenerPuntajes();
        agregarElementos();
    }
    public void agregarElementos(){
        agregarPanel();
        agregarTabla();
    }
    public void agregarPanel(){
        panel = new Fondo();
        panel.setBounds(0,0,400,400);
        panel.setLayout(null);
        this.getContentPane().add(panel);
    }

    public void agregarTabla(){
       modelo = new DefaultTableModel();
        modelo.addColumn("Lugar");
        modelo.addColumn("Nombre Jugador");
        modelo.addColumn("Fecha");
        modelo.addColumn("Puntaje");
        String[] datos = new String[4];
        for (int i =0;i<consulta.getDatosPuntaje().size();i++){
            datos[0]=String.valueOf(consulta.getDatosPuntaje().get(i).getContador());
            datos[1]=consulta.getDatosPuntaje().get(i).getNombreJugador();
            datos[2]=consulta.getDatosPuntaje().get(i).getFecha();
            datos[3]=String.valueOf(consulta.getDatosPuntaje().get(i).getPuntaje());
            modelo.addRow(datos);
        }
        JTable tabla = new JTable(modelo);
        tabla.setBounds(10,10,380,250);
        tabla.setBackground(new Color(133, 33, 247));
        tabla.setForeground(Color.white);
        panel.add(tabla);

        JScrollPane scrolTabla= new JScrollPane(tabla);
        scrolTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrolTabla.setBounds(10, 10, 380, 250);
        panel.add(scrolTabla);

        JButton botonSalir = new JButton("Salir");
        botonSalir.setForeground(new Color(227, 226, 229 ));
        botonSalir.setBackground(Color.BLUE);
        botonSalir.setBounds(70,300,100,30);
        panel.add(botonSalir);
        botonSalir.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
             botonSalir.setForeground(Color.white);
             botonSalir.setBackground(new Color(23, 149, 255 ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
             botonSalir.setForeground(new Color(227, 226, 229 ));
             botonSalir.setBackground(Color.BLUE);
            }
        });

        JButton botonEliminar= new JButton("Vaciar la lista");
        botonEliminar.setBounds(220,300,120,30);
        botonEliminar.setBackground(Color.red);
        botonEliminar.setForeground(new Color(227, 226, 229 ));
        panel.add(botonEliminar);

        botonEliminar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
             consulta.limpiar();
             for (int i=modelo.getRowCount()-1;i>=0;i--){
                 modelo.removeRow(i);
             }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            botonEliminar.setForeground(Color.white);
            botonEliminar.setBackground(new Color(255, 68, 23 ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonEliminar.setForeground(new Color(227, 226, 229 ));
                botonEliminar.setBackground(Color.red);
            }
        });
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
class Fondo extends JPanel{
    public void paint(Graphics g){
        Image imagen = new ImageIcon(getClass().getResource("/imagenes/fondoTabla.jpg")).getImage();
        g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}
