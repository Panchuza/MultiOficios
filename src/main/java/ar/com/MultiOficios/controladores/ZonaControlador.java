package ar.com.MultiOficios.controladores;

import ar.com.MultiOficios.entidades.Usuario;
import ar.com.MultiOficios.errores.ErrorServicio;
import ar.com.MultiOficios.servicios.UsuarioServicio;
import ar.com.MultiOficios.servicios.ZonaServicio;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/zona")
public class ZonaControlador {

    @Autowired
    private ZonaServicio zonaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/perfil")
    public String perfil(HttpSession session, ModelMap modelo) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        
        return "perfil.html";
    }

    @PostMapping("/perfil")
    public String crearZona(@RequestParam int codigoPostal, @RequestParam String ciudad,
            @RequestParam String calle, @RequestParam int numero,
            @RequestParam String provincia, RedirectAttributes attr) throws ErrorServicio {

        try {

            zonaServicio.crearZona(codigoPostal, ciudad, calle, numero, provincia);

            attr.addFlashAttribute("exito", "se registro la zona correctamente");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/zona/perfil";
    }
    


}
