package ar.com.MultiOficios.controladores;

import ar.com.MultiOficios.entidades.Usuario;
import ar.com.MultiOficios.enums.RolUsuario;
import ar.com.MultiOficios.errores.ErrorServicio;
import ar.com.MultiOficios.servicios.UsuarioServicio;
import ar.com.MultiOficios.servicios.ZonaServicio;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ZonaServicio zonaServicio;
    
    @GetMapping("form")
    public String form() {
        return "form.html";
    }

    
    @PostMapping("form")
    public String crearUsuario(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String email, @RequestParam String password,
            @RequestParam String confirmarPassword, 
            @RequestParam RolUsuario rolUsuario, RedirectAttributes attr) {

        try {
            
            usuarioServicio.crearUsuario(nombre, apellido, email, password, confirmarPassword, rolUsuario);

            attr.addFlashAttribute("exito", "se registro el usuario'" + nombre + "' correctamente");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuario/form";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/listarUsuarios")
    public String listarUsuarios(String id, ModelMap model, @RequestParam(required = false) String query) throws ErrorServicio {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        model.put("usuarios", usuarios);
        return "listarUsuarios.html";
    }

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN'")
    @GetMapping("/bajaUsuario/{id}")
    public String bajaUsuario(@PathVariable("id") String id, RedirectAttributes attr) {
        try {
            usuarioServicio.darDeBajaUsuario(id);
            attr.addAttribute("exito", "Usuario dado de baja");
        } catch (ErrorServicio ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            attr.addAttribute("error", ex.getMessage());
        }
        return "redirect:/usuario/listarUsuarios";
    }

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN'")
    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable("id") String id, ModelMap model) {

        Usuario usuario = new Usuario();
        try {
            usuario = usuarioServicio.buscarPorId(id);
            model.put("usuario", usuario);
        } catch (ErrorServicio ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "modificar-usuario.html";
    }

    @PostMapping("/editarUsuario")
    public String editarUsuario(@RequestParam String id, @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido, Date fechaModificacionUsuario, 
            ModelMap model, RedirectAttributes attr) throws Exception {
        try {
            usuarioServicio.modificarUsuario(id, nombre, apellido, fechaModificacionUsuario);
            attr.addFlashAttribute("exito", "Usuario editado correctamente");
        } catch (ErrorServicio ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            attr.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/usuario/listarUsuarios";

    }

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN'")
    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") String id, RedirectAttributes attr) throws Exception {
        try {
            usuarioServicio.eliminarUsuario(id);
            attr.addFlashAttribute("exito", "Usuario eliminado");
        } catch (ErrorServicio ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            attr.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/usuario/listarUsuarios";
    }
}
