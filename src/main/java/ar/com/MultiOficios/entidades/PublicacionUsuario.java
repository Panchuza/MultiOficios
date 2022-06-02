package ar.com.MultiOficios.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class PublicacionUsuario {
    
    @Id
    @GeneratedValue(generator= "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacionUsuario;
    
    private String tipoPublicacion;
    
    @OneToMany
    private List<Publicacion> publicaciones;

    public PublicacionUsuario() {
    }

    public PublicacionUsuario(String id, Date fechaPublicacionUsuario, String tipoPublicacion, List<Publicacion> publicaciones) {
        this.id = id;
        this.fechaPublicacionUsuario = fechaPublicacionUsuario;
        this.tipoPublicacion = tipoPublicacion;
        this.publicaciones = publicaciones;
    }

    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the fechaPublicacionUsuario
     */
    public Date getFechaPublicacionUsuario() {
        return fechaPublicacionUsuario;
    }

    /**
     * @param fechaPublicacionUsuario the fechaPublicacionUsuario to set
     */
    public void setFechaPublicacionUsuario(Date fechaPublicacionUsuario) {
        this.fechaPublicacionUsuario = fechaPublicacionUsuario;
    }

    /**
     * @return the tipoPublicacion
     */
    public String getTipoPublicacion() {
        return tipoPublicacion;
    }

    /**
     * @param tipoPublicacion the tipoPublicacion to set
     */
    public void setTipoPublicacion(String tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    /**
     * @return the publicaciones
     */
    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    /**
     * @param publicaciones the publicaciones to set
     */
    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    
  
}
