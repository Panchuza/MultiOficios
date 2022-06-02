package ar.com.MultiOficios.servicios;

import ar.com.MultiOficios.entidades.Publicacion;
import ar.com.MultiOficios.entidades.PublicacionUsuario;
import ar.com.MultiOficios.entidades.Usuario;
import ar.com.MultiOficios.errores.ErrorServicio;
import ar.com.MultiOficios.repositorios.PublicacionUsuarioRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublicacionUsuarioServicio {
    
    @Autowired
    private PublicacionUsuarioRepositorio publicacionUsuarioRepositorio;
    @Autowired
    private PublicacionServicio publicacionServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Transactional(rollbackFor = {Exception.class})
    public PublicacionUsuario crearPU(String tipoPublicacion, String idUsuario, String idPublicacion, 
            String nombre, String descripcion) throws ErrorServicio{
        
        PublicacionUsuario publicacionUsuario = new PublicacionUsuario();
        publicacionUsuario.setFechaPublicacionUsuario(new Date());
        publicacionUsuario.setTipoPublicacion(tipoPublicacion);
        
        Usuario usuario = usuarioServicio.buscarPorId(idUsuario);
        Publicacion publicacion = publicacionServicio.buscarPorId(idPublicacion);
        
//        publicacionUsuario.setUsuario(usuario);
//        publicacionUsuario.setPublicacion(publicacion);
        publicacionServicio.crear(nombre, descripcion);

        return publicacionUsuarioRepositorio.save(publicacionUsuario);
        
    }
    
    @Transactional(rollbackFor = {Exception.class})
    public PublicacionUsuario crearPU2(String tipoPublicacion, String idUsuario, String idPublicacion, 
            String nombre, String descripcion) throws ErrorServicio{
        
        PublicacionUsuario publicacionUsuario = new PublicacionUsuario();
        publicacionUsuario.setFechaPublicacionUsuario(new Date());
        publicacionUsuario.setTipoPublicacion(tipoPublicacion);
        
        Usuario usuario = usuarioServicio.buscarPorId(idUsuario);
        Publicacion publicacion = publicacionServicio.buscarPorId(idPublicacion);
        
//        publicacionUsuario.setUsuario(usuario);
//        publicacionUsuario.setPublicacion(publicacion);
//        publicacionServicio.crearContratar(idPublicacion);

        return publicacionUsuarioRepositorio.save(publicacionUsuario);
        
    }
}
