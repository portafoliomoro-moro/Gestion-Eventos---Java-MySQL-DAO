package gestion_eventos.datos;

import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import gestion_eventos.dominio.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static gestion_eventos.conexion.Conexion.getConexion;

public class EventoDAO implements IEventoDAO{
    @Override
    public List<Evento> listarEventos() {
        List<Evento> eventos = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM eventos ORDER BY id_evento";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var evento = new Evento();
                evento.setId_evento(rs.getInt("id_evento"));
                evento.setFecha(rs.getDate("fecha").toLocalDate());
                evento.setTema(rs.getString("tema"));
                evento.setDescripcion(rs.getString("descripcion"));
                evento.setColegios_participantes(rs.getString("colegios_participantes"));
                evento.setNumero_asistentes(rs.getInt("numero_asistentes"));
                eventos.add(evento);
            }
        }catch(Exception e){
            System.out.println("Error al listar los eventos: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return eventos;
    }

    @Override
    public boolean buscarEventoPorId(Evento evento) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM eventos WHERE id_evento=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, evento.getId_evento());
            rs = ps.executeQuery();
            if(rs.next()){
                evento.setId_evento(rs.getInt("id_evento"));
                evento.setFecha(rs.getDate("fecha").toLocalDate());
                evento.setTema(rs.getString("tema"));
                evento.setDescripcion(rs.getString("descripcion"));
                evento.setColegios_participantes(rs.getString("colegios_participantes"));
                evento.setNumero_asistentes(rs.getInt("numero_asistentes"));
                return true;
            }
        }catch(Exception e){
            System.out.println("Error al buscar el evento: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarEvento(Evento evento) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "INSERT INTO eventos (fecha, tema, descripcion, colegios_participantes, numero_asistentes) VALUE (?,?,?,?,?)";
        try{
            LocalDate fechaEvento = evento.getFecha();
            // Convertimos LocalDate a java.sql.Date
            Date fecha = Date.valueOf(fechaEvento);

            ps = con.prepareStatement(sql);
            ps.setDate(1, fecha);
            ps.setString(2, evento.getTema());
            ps.setString(3, evento.getDescripcion());
            ps.setString(4, evento.getColegios_participantes());
            ps.setInt(5, evento.getNumero_asistentes());
            ps.execute();
            return true;

        }catch(Exception e){
            System.out.println("Error al agregar el evento: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarEvento(Evento evento) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "UPDATE eventos SET fecha=?, tema=?, descripcion=?, colegios_participantes=?, numero_asistentes=?" +
                " WHERE id_evento=?";
        try{
            LocalDate fechaEvento = evento.getFecha();
            // Convertimos LocalDate a java.sql.Date
            Date fecha = Date.valueOf(fechaEvento);

            ps = con.prepareStatement(sql);
            ps.setDate(1, fecha);
            ps.setString(2, evento.getTema());
            ps.setString(3, evento.getDescripcion());
            ps.setString(4, evento.getColegios_participantes());
            ps.setInt(5, evento.getNumero_asistentes());
            ps.setInt(6, evento.getId_evento());
            ps.execute();
            return true;

        }catch(Exception e){
            System.out.println("Error al modificar el evento: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarEvento(Evento evento) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "DELETE FROM eventos WHERE id_evento=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, evento.getId_evento());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al eliminar el evento: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        IEventoDAO eventoDAO = new EventoDAO();
//        System.out.println("*** Listar ***");
//        var listar = eventoDAO.listarEventos();
//        listar.forEach(System.out::println);

//        System.out.println("*** Buscar ***");
//        var buscar = new Evento(1);
//        var encontrado = eventoDAO.buscarEventoPorId(buscar);
//        if(encontrado){
//            System.out.println("Evento encontrado: " + buscar);
//        }
//        else{
//            System.out.println("Evento No encontrado: " + buscar);
//        }
//        System.out.println("*** Eliminar ***");
//        LocalDate fecha = LocalDate.of(2025, 11, 9);
//        var eliminar = new Evento(2);
//        var eliminado = eventoDAO.eliminarEvento(eliminar);
//        if(eliminado){
//            System.out.println("Evento eliminado: " + eliminar);
//        }
//        else{
//            System.out.println("Evento No eliminado: " + eliminar);
//        }
//    }
}
