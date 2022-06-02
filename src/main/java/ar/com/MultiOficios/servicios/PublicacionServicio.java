package ar.com.MultiOficios.servicios;

import ar.com.MultiOficios.entidades.Publicacion;
import ar.com.MultiOficios.enums.EstadoPublicacion;
import ar.com.MultiOficios.errores.ErrorServicio;
import ar.com.MultiOficios.repositorios.PublicacionRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublicacionServicio {

    @Autowired
    public PublicacionRepositorio publicacionRepositorio;

//---------------------------------------------CREAR PUBLICACION--------------------------------------------------
    @Transactional(rollbackFor = {Exception.class})
    public void crear(String nombre, String descripcion) throws ErrorServicio {
        validar(nombre, descripcion);

        Publicacion publicacion = new Publicacion();

        publicacion.setNombre(nombre);
        publicacion.setDescripcion(descripcion);
        publicacion.setFechaAltaPublicacion(new Date());
        publicacion.setFechaBajaPublicacion(null);
        publicacion.setFechaModificacionPublicacion(null);
        publicacion.setEstadoPublicacion(EstadoPublicacion.PUBLICADO);

        publicacionRepositorio.save(publicacion);
    }

//    @Transactional(rollbackFor = {Exception.class})
//    public void crearContratar(String id) throws ErrorServicio{
//        Publicacion publicacion = buscarPorId(id);
//        publicacion.setEstadoPublicacion(EstadoPublicacion.CONTRATADO);
//        publicacionRepositorio.save(publicacion);
//    }
//---------------------------------------------MODIFICAR PUBLICACION--------------------------------------------------
    @Transactional(rollbackFor = {Exception.class})
    public void modificar(String id, String nombre, String descripcion) throws ErrorServicio {

        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);

        if (respuesta.isPresent() && respuesta.get().getFechaBajaPublicacion() == null) {

            Publicacion publicacion = respuesta.get();
            publicacion.setNombre(nombre);
            publicacion.setDescripcion(descripcion);
            publicacion.setFechaModificacionPublicacion(new Date());

            publicacionRepositorio.save(publicacion);
        } else {
            throw new ErrorServicio("La publicacion esta dada de baja");
        }

    }

    //------------------------------------------DAR DE BAJA LA PUBLICACION--------------------------------------------------
    @Transactional(rollbackFor = {Exception.class})
    public void darDeBajaPublicacion(String id) throws ErrorServicio {

        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);

        if (respuesta.get().getFechaBajaPublicacion() == null) {

            Publicacion publicacion = respuesta.get();

            publicacion.setFechaBajaPublicacion(new Date());

            publicacionRepositorio.save(publicacion);
        } else {
            throw new ErrorServicio("La Publicacion no existe");
        }

    }

    //------------------------------------------ELIMINAR LA PUBLICACION--------------------------------------------------
    @Transactional(rollbackFor = {Exception.class})
    public void eliminarPublicacion(String id) throws ErrorServicio {

        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);

        if (respuesta.isPresent()) {
            publicacionRepositorio.deleteById(id);
        } else {
            throw new ErrorServicio("La Publicacion no existe");
        }

    }

//------------------------------------------CONSULTAS--------------------------------------------------
//------------------------------------------CONSULTA POR ID--------------------------------------------------
    @Transactional(readOnly = true)
    public Publicacion buscarPorId(String id) throws ErrorServicio {

        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);

        if (respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new ErrorServicio("No se encontro la publicacion");
        }
    }

//------------------------------------------CONSULTA POR NOMBRE y DESCRIPCION--------------------------------------------------
    @Transactional(readOnly = true)
    public List<Publicacion> buscarPorNombre(String consulta) {

        return publicacionRepositorio.buscarPorNombrePordescripcion(consulta);

    }

//------------------------------------------LISTAR LAS PUBLICACIONES--------------------------------------------------
    @Transactional(readOnly = true)
    public List<Publicacion> listarPublicaciones() throws ErrorServicio {
        List<Publicacion> respuesta = publicacionRepositorio.findAll();
        if (respuesta == null) {
            throw new ErrorServicio("No hay Publicaciones");
        } else {

            return publicacionRepositorio.findAll();
        }
    }

//------------------------------------------VALIDACION DE DATOS (NOMBRE Y DESCRIPCION)--------------------------------------------------
    public void validar(String nombre, String descripcion) throws ErrorServicio {

        if (nombre.isEmpty() || nombre == null) {
            throw new ErrorServicio("El nombre de la publicacion no puede estar vacio");
        }

        if (descripcion.isEmpty() || descripcion == null) {
            throw new ErrorServicio("El nombre de la descripcion no puede estar vacio");
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void modificarEstado(String id) throws ErrorServicio {

        Optional<Publicacion> respuesta = publicacionRepositorio.findById(id);

        if (respuesta.isPresent() && respuesta.get().getFechaBajaPublicacion() == null) {
            Publicacion publicacion = respuesta.get();
            if (publicacion.getEstadoPublicacion().equals(EstadoPublicacion.PUBLICADO)) {
                publicacion.setEstadoPublicacion(EstadoPublicacion.CONTRATADO);
            } else {
                publicacion.setEstadoPublicacion(EstadoPublicacion.PUBLICADO);
            }
            publicacionRepositorio.save(publicacion);
        } else {
            throw new ErrorServicio("La publicacion no esta");
        }

    }
}
