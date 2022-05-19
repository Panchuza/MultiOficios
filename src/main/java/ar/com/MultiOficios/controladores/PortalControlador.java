package ar.com.MultiOficios.controladores;

import ar.com.MultiOficios.entidades.Usuario;
import ar.com.MultiOficios.errores.ErrorServicio;
import ar.com.MultiOficios.repositorios.UsuarioRepositorio;
import ar.com.MultiOficios.servicios.UsuarioServicio;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    UsuarioServicio usuarioServicio;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/logout2")
    public String logout2() {
        return "logout2.html";
    }

    @GetMapping("/perfilEditar")
    public String perfil() throws ErrorServicio {
        return "perfilEditar.html";
    }

//    @GetMapping("/perfilEditar/{id}")
//    public String perfil(ModelMap model,@PathVariable("id") String id) throws ErrorServicio {
// 
//       model.put("usuario", usuarioServicio.buscarPorId(id));
//
//        return "perfilEditar.html";
//    }
//
    @PostMapping("/perfilEditar")
    public String editarUsuarioPerfil(HttpSession session, @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido, ModelMap modelo) throws ErrorServicio, Exception {

        Usuario usuario = (Usuario) session.getAttribute("usuariosession");

        try {

            Usuario modificado = usuarioServicio.modificarUsuarioPerfil(usuario.getId(), nombre, apellido);
            session.setAttribute("usuariosession", modificado);
            
            modelo.put("exito", "Usuario editado correctamente");

        } catch (ErrorServicio ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            return "perfilEditar.html";
        }

        return "redirect:/";
    }

}
