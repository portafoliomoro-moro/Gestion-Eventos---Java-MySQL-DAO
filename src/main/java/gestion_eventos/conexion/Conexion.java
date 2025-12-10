package gestion_eventos.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;

        var baseDatos = "biblioteca_db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch(Exception e){
            System.out.println("Error al conectar la bd: " + e.getMessage());
        }
        return conexion;
    }

//    public static void main(String[] args) {
//        var conexion = Conexion.getConexion();
//        if(conexion != null){
//            System.out.println("Conexión exitosa: " + conexion);
//        }
//        else{
//            System.out.println("Error de conexión: " + conexion);
//        }
//    }
}
