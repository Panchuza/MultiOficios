package ar.com.MultiOficios.servicios;

import ar.com.MultiOficios.entidades.Usuario;
import ar.com.MultiOficios.enums.RolUsuario;
import ar.com.MultiOficios.errores.ErrorServicio;
import ar.com.MultiOficios.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    public UsuarioRepositorio usuarioRepositorio;
    
//---------------------------------------------USUARIO--------------------------------------------------------
    @Transactional(rollbackFor = {Exception.class})
    public void crearUsuario(String nombre, String apellido, String email, String password, String confirmarPassword, RolUsuario rolUsuario) throws Exception {
        validarDatos(nombre, apellido, email, password, confirmarPassword);
        String passwordEncriptado = new BCryptPasswordEncoder().encode(password);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setPassword(passwordEncriptado);
        usuario.setFechaAltaUsuario(new Date());
        usuario.setFechaBajaUsuario(null);
        usuario.setRolUsuario(rolUsuario.CLIENTE);

        usuarioRepositorio.save(usuario);
    }
    
    public void darDeBajaUsuario(String id, String nombre) throws ErrorServicio{    
        validar(nombre);
        Usuario usuario = buscarPorId(id);
        if (usuario.getFechaBajaUsuario() == null) {
            usuario.setFechaBajaUsuario(new Date());
            usuarioRepositorio.save(usuario);
        } else {
            throw new ErrorServicio("No se encontro el usuario solicitado");
        }
    }
    
    public void modificarUsuario(String id, String nombre, String apellido, String email) throws ErrorServicio {

        validar(nombre);
        Usuario usuario = buscarPorId(id);

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);

        usuarioRepositorio.save(usuario);
    }
    
    @Transactional(rollbackFor = {Exception.class})
    public void eliminarUsuario(String id) throws Exception {
        usuarioRepositorio.deleteById(buscarPorId(id).getId());
    }
    
//---------------------------------------------USUARIO--------------------------------------------------------
//----------------------------------------CONSULTAS DE USUARIO------------------------------------------------   
    @Transactional(readOnly = true)
    public Usuario buscarPorId(String id) throws ErrorServicio{
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if(respuesta.isPresent()){
            return respuesta.get();
        } else {
            throw new ErrorServicio("No se encontro el usuario");
        }
    }
  
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorRol(String rol){ //se debe recibir un string del enum de rol
        return usuarioRepositorio.buscarPorRol(rol);
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorNombreApellidoEmail(String query){ //aca recibo un nombre O un apellido O un email y lo busca
        return usuarioRepositorio.buscarPorNombreApellidoEmail(query);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) throws Exception {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        if (usuario != null) {
            return usuario;
        } else {
            throw new Exception("No se encontró el usuario");
        }
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

//----------------------------------------CONSULTAS DE USUARIO------------------------------------------------   
//-----------------------------------------VALIDAR LOS DATOS--------------------------------------------------   


    public void validarDatos(String nombre, String apellido, String email, String password, String confirmarPassword) throws Exception {
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Error: El nombre del Usuario no puede ser nulo");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new Exception("Error: El apellido del Usuario no puede ser nulo");
        }
        if (email == null || email.isEmpty()) {
            throw new Exception("Error: El email del Usuario no puede ser nulo");
        }
        if (password == null || password.isEmpty()) {
            throw new Exception("Error: La contraseña del Usuario no puede ser nula");
        }
        if (confirmarPassword == null || confirmarPassword.isEmpty()) {
            throw new Exception("Error: La confirmacion de contraseña del Usuario no puede ser nula");
        }
        if (!password.equals(confirmarPassword)) {
            throw new Exception("Las contraseñas deben ser iguales");
        }
    }
    public void validar(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
    }
//-----------------------------------------VALIDAR LOS DATOS--------------------------------------------------   
//---------------------------------------------SEGURIDAD------------------------------------------------------   

    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = null;
        try {
            usuario = buscarPorEmail(email);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (usuario.getFechaBajaUsuario() != null) {
            throw new UsernameNotFoundException("El usuario está dado de baja");
        }
        if (usuario == null) {
            return null;
        }

        List<GrantedAuthority> permisos = new ArrayList<>();

        GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuario.getRolUsuario().toString());
        permisos.add(p1);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("usuariosession", usuario);

        return new User(usuario.getEmail(), usuario.getPassword(), permisos);
    }
//---------------------------------------------SEGURIDAD------------------------------------------------------   

}
