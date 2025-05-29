package test.roshka.test_java.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "noticia")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoticiaNews {

    public String titulo;
    public String resumen;
    public String fecha;
    public String enlace;
    public String enlace_foto;

    // BONUS
    public String contenido_foto;
    public String content_type_foto;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getEnlace_foto() {
        return enlace_foto;
    }

    public void setEnlace_foto(String enlace_foto) {
        this.enlace_foto = enlace_foto;
    }

    public String getContenido_foto() {
        return contenido_foto;
    }

    public void setContenido_foto(String contenido_foto) {
        this.contenido_foto = contenido_foto;
    }

    public String getContent_type_foto() {
        return content_type_foto;
    }

    public void setContent_type_foto(String content_type_foto) {
        this.content_type_foto = content_type_foto;
    }
}
