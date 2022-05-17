package ar.com.MultiOficios.controladores;

import ar.com.MultiOficios.entidades.Usuario;
import ar.com.MultiOficios.errores.ErrorServicio;
import ar.com.MultiOficios.repositorios.UsuarioRepositorio;
import ar.com.MultiOficios.servicios.UsuarioServicio;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public String perfilEditar() {
        return "perfilEditar.html";
    }
    
//    @PostMapping("/editarPerfil")
//    public String editarUsuarioPerfil(@RequestParam String id, @RequestParam(required = false) String nombre,
//            @RequestParam(required = false) String apellido,Date fechaModificacionUsuario, 
//            ModelMap model, RedirectAttributes attr) throws ErrorServicio, Exception {
//        try {
//            usuarioServicio.modificarUsuarioPerfil(id, nombre, apellido, fechaModificacionUsuario);
//            attr.addFlashAttribute("exito", "Usuario editado correctamente");
//        } catch (ErrorServicio ex) {
//            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
//            attr.addFlashAttribute("error", ex.getMessage());
//        }
//        return "redirect:/zona/perfil";
//
//    }

}
