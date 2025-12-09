package gestion_eventos.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Evento {
    private int id_evento;
    private LocalDate fecha;
    private String tema;
    private String descripcion;
    private String colegios_participantes;
    private int numero_asistentes;

    public Evento() {
    }

    public Evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public Evento(LocalDate fecha, String tema, String descripcion, String colegios_participantes, int numero_asistentes) {
        this.fecha = fecha;
        this.tema = tema;
        this.descripcion = descripcion;
        this.colegios_participantes = colegios_participantes;
        this.numero_asistentes = numero_asistentes;
    }

    public Evento(int id_evento, LocalDate fecha, String tema, String descripcion, String colegios_participantes, int numero_asistentes) {
        this(fecha, tema, descripcion, colegios_participantes, numero_asistentes);
        this.id_evento = id_evento;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColegios_participantes() {
        return colegios_participantes;
    }

    public void setColegios_participantes(String colegios_participantes) {
        this.colegios_participantes = colegios_participantes;
    }

    public int getNumero_asistentes() {
        return numero_asistentes;
    }

    public void setNumero_asistentes(int numero_asistentes) {
        this.numero_asistentes = numero_asistentes;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id_evento=" + id_evento +
                ", fecha=" + fecha +
                ", tema='" + tema + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", colegios_participantes='" + colegios_participantes + '\'' +
                ", numero_asistentes=" + numero_asistentes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return id_evento == evento.id_evento && numero_asistentes == evento.numero_asistentes && Objects.equals(fecha, evento.fecha) && Objects.equals(tema, evento.tema) && Objects.equals(descripcion, evento.descripcion) && Objects.equals(colegios_participantes, evento.colegios_participantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_evento, fecha, tema, descripcion, colegios_participantes, numero_asistentes);
    }
}