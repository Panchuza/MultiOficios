
package ar.com.MultiOficios.entidades;

import ar.com.MultiOficios.enums.EstadoPublicacion;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity 
public class Publicacion {
    
    @Id
    @GeneratedValue(generator= "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String id;
    private String nombre;
    private String descripcion;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAltaPublicacion;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBajaPublicacion;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacionPublicacion;
    
    @Enumerated(EnumType.STRING)
    private EstadoPublicacion estadoPublicacion;
    

    public Publicacion(String id, String nombre, String descripcion, Date fechaAltaPublicacion, Date fechaBajaPublicacion, Date fechaModificacionPublicacion, EstadoPublicacion estadoPublicacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaAltaPublicacion = fechaAltaPublicacion;
        this.fechaBajaPublicacion = fechaBajaPublicacion;
        this.fechaModificacionPublicacion = fechaModificacionPublicacion;
        this.estadoPublicacion = estadoPublicacion;
    }

    public Publicacion() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAltaPublicacion() {
        return fechaAltaPublicacion;
    }

    public void setFechaAltaPublicacion(Date fechaAltaPublicacion) {
        this.fechaAltaPublicacion = fechaAltaPublicacion;
    }

    public Date getFechaBajaPublicacion() {
        return fechaBajaPublicacion;
    }

    public void setFechaBajaPublicacion(Date fechaBajaPublicacion) {
        this.fechaBajaPublicacion = fechaBajaPublicacion;
    }

    public Date getFechaModificacionPublicacion() {
        return fechaModificacionPublicacion;
    }

    public void setFechaModificacionPublicacion(Date fechaModificacionPublicacion) {
        this.fechaModificacionPublicacion = fechaModificacionPublicacion;
    }

    /**
     * @return the estadoPublicacion
     */
    public EstadoPublicacion getEstadoPublicacion() {
        return estadoPublicacion;
    }

    /**
     * @param estadoPublicacion the estadoPublicacion to set
     */
    public void setEstadoPublicacion(EstadoPublicacion estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }
    
    @Override
    public String toString() {
        return "Publicacion{" + "id=" + getId() + ", nombre=" + getNombre() + ", descripcion=" + getDescripcion() + ", fechaAltaPublicacion=" + getFechaAltaPublicacion() + ", fechaBajaPublicacion=" + getFechaBajaPublicacion() + ", fechaModificacionPublicacion=" + getFechaModificacionPublicacion() + '}';
    }
    
}
