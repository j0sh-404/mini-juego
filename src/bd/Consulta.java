package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Consulta extends Conexion{
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conexion=null;
    private ArrayList<DatosPuntaje>datosPuntaje= new ArrayList<>();
    private int contadorPuntaje=1;
    public void insertarPuntaje(String nombreJugador,String fecha, int puntaje){
    conexion=getConnection();
        try {
            ps=conexion.prepareStatement("insert into puntaje(nombreJugador,fecha,puntaje) values(?,?,?)");
            ps.setString(1,nombreJugador);
            ps.setString(2,fecha);
            ps.setInt(3,puntaje);
            int i=ps.executeUpdate();
            conexion.close();
        } catch (SQLException throwables) {
            System.err.println("Erro en la consulta insertar puntaje"+throwables);
        }
    }
    public void obtenerPuntajes(){
    conexion=getConnection();
        try {
            ps=conexion.prepareStatement("select * from puntaje order by puntaje desc");
            rs=ps.executeQuery();
            while (rs.next()){
             datosPuntaje.add(new DatosPuntaje(contadorPuntaje,rs.getString(2),rs.getString(3),rs.getInt(4)));
             contadorPuntaje++;
            }
            conexion.close();
        } catch (SQLException throwables) {
            System.err.println("Error "+throwables);
        }
    }

    public void limpiar(){
        conexion=getConnection();
        try {
            ps=conexion.prepareStatement("delete from puntaje");
            ps.executeUpdate();
            conexion.close();
        } catch (SQLException throwables) {

        }
    }

    public ArrayList<DatosPuntaje> getDatosPuntaje() {
        return datosPuntaje;
    }

    public void setDatosPuntaje(ArrayList<DatosPuntaje> datosPuntaje) {
        this.datosPuntaje = datosPuntaje;
    }
}
