package ar.com.MultiOficios.entidades;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeNacimiento;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAltaUsuario;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBajaUsuario;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacionUsuario;
    
//    @OneToMany
//    private Publicacion publicacion;
//    
//    @ManyToOne
//    private Zona zona;

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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fechaDeNacimiento
     */
    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    /**
     * @param fechaDeNacimiento the fechaDeNacimiento to set
     */
    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    /**
     * @return the fechaAltaUsuario
     */
    public Date getFechaAltaUsuario() {
        return fechaAltaUsuario;
    }

    /**
     * @param fechaAltaUsuario the fechaAltaUsuario to set
     */
    public void setFechaAltaUsuario(Date fechaAltaUsuario) {
        this.fechaAltaUsuario = fechaAltaUsuario;
    }

    /**
     * @return the fechaBajaUsuario
     */
    public Date getFechaBajaUsuario() {
        return fechaBajaUsuario;
    }

    /**
     * @param fechaBajaUsuario the fechaBajaUsuario to set
     */
    public void setFechaBajaUsuario(Date fechaBajaUsuario) {
        this.fechaBajaUsuario = fechaBajaUsuario;
    }

    /**
     * @return the fechaModificacionUsuario
     */
    public Date getFechaModificacionUsuario() {
        return fechaModificacionUsuario;
    }

    /**
     * @param fechaModificacionUsuario the fechaModificacionUsuario to set
     */
    public void setFechaModificacionUsuario(Date fechaModificacionUsuario) {
        this.fechaModificacionUsuario = fechaModificacionUsuario;
    }
    
    
}
