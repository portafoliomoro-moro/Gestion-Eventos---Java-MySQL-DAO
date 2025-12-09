package gestion_eventos.presentacion;

import gestion_eventos.datos.EventoDAO;
import gestion_eventos.datos.IEventoDAO;
import gestion_eventos.dominio.Evento;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EventosApp {
    public static void main(String[] args) {
        eventosApp();
    }

    private static void eventosApp(){
        var salir = false;
        var consola = new Scanner(System.in);

        IEventoDAO eventoDAO = new EventoDAO();
        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpcion(opcion, consola, eventoDAO);
            }catch(Exception e){
                System.out.println("Error al ingresar una opción: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                *** Gestión de Eventos ***
                1. Listar Eventos
                2. Buscar Evento
                3. Agregar Evento
                4. Modificar Evento
                5. Eliminar Evento
                6. Salir
                Elige una opción:\s""");

        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpcion(int opcion, Scanner consola, IEventoDAO eventoDAO){
        var salir = false;
        switch(opcion){
            case 1 -> {
                System.out.println("--- Listado de Eventos ---");
                var listarEventos = eventoDAO.listarEventos();
                listarEventos.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("--- Buscar Evento ---");
                System.out.print("Id del evento a buscar: ");
                var idEvento = Integer.parseInt(consola.nextLine());
                var buscarEvento = new Evento(idEvento);
                var encontrado = eventoDAO.buscarEventoPorId(buscarEvento);
                if(encontrado){
                    System.out.println("Evento encontrado: " + buscarEvento);
                }
                else{
                    System.out.println("Evento No encontrado: " + buscarEvento);
                }
            }
            case 3 -> {
                System.out.println("--- Agregar Evento ---");
                System.out.print("Fecha (yyyy-MM-dd): ");
                var fechaStr = consola.nextLine();
                LocalDate fecha = null;
                try {
                    fecha = LocalDate.parse(fechaStr);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha inválido. Usa yyyy-MM-dd.");
                    break;
                }
                System.out.print("Tema: ");
                var tema = consola.nextLine();
                System.out.print("Descripción: ");
                var descripcion = consola.nextLine();
                System.out.print("Colegios participantes: ");
                var colegiosParticipantes = consola.nextLine();
                System.out.print("Número de asistentes: ");
                var numeroAsistentes = Integer.parseInt(consola.nextLine());
                var agregarEvento = new Evento(fecha, tema, descripcion, colegiosParticipantes, numeroAsistentes);
                var agregado = eventoDAO.agregarEvento(agregarEvento);
                if(agregado){
                    System.out.println("Evento agregado: " + agregarEvento);
                }
                else{
                    System.out.println("Evento No agregado: " + agregarEvento);
                }
            }
            case 4 -> {
                System.out.println("--- Modificar Evento ---");
                System.out.print("Id del evento a modificar: ");
                var idEvento = Integer.parseInt(consola.nextLine());
                var buscar = new Evento(idEvento);
                var encontrado = eventoDAO.buscarEventoPorId(buscar);
                if(encontrado){
                    System.out.print("Fecha (yyyy-MM-dd): ");
                    var fechaStr = consola.nextLine();
                    LocalDate fecha = null;
                    try {
                        fecha = LocalDate.parse(fechaStr);
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de fecha inválido. Usa yyyy-MM-dd.");
                        break;
                    }
                    System.out.print("Tema: ");
                    var tema = consola.nextLine();
                    System.out.print("Descripción: ");
                    var descripcion = consola.nextLine();
                    System.out.print("Colegios participantes: ");
                    var colegiosParticipantes = consola.nextLine();
                    System.out.print("Número de asistentes: ");
                    var numeroAsistentes = Integer.parseInt(consola.nextLine());
                    var modificarEvento = new Evento(idEvento, fecha, tema, descripcion, colegiosParticipantes, numeroAsistentes);
                    var modificaro = eventoDAO.modificarEvento(modificarEvento);
                    System.out.println("Evento modificado: " + modificarEvento);
                }
                else{
                    System.out.println("Evento No encontrado: " + buscar);
                }
            }
            case 5 -> {
                System.out.println("--- Eliminar Evento ---");
                System.out.print("Id del evento a eliminar: ");
                var idEvento = Integer.parseInt(consola.nextLine());
                var buscar = new Evento(idEvento);
                var encontrado = eventoDAO.buscarEventoPorId(buscar);
                if(encontrado){
                    eventoDAO.eliminarEvento(buscar);
                    System.out.println("Evento eliminado: " + buscar);
                }
                else{
                    System.out.println("Evento No encontrado: " + buscar);
                }
            }
            case 6 -> {
                System.out.println("Hasta pronto!");
                salir = true;
            }
            default -> System.out.println("Opción No reconocida: " + opcion);
        }
        return salir;
    }
}
