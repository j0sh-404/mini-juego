package bd;

import java.sql.*;

public class Conexion {
private Statement st;

public Connection getConnection(){
    Connection conexion=null;
    try {
        Class.forName("org.sqlite.JDBC");
        conexion= DriverManager.getConnection("jdbc:sqlite:puntajeJugador.db");
        st=conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
    } catch (ClassNotFoundException e) {
        System.err.println("Error al conectar "+ e);
    } catch (SQLException a) {
        System.err.println("Error de archivo sqlite "+a);
    }
    if (conexion==null){
        System.out.println("es nulo");
    }
    return  conexion;
}
}
