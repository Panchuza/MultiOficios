package ar.com.MultiOficios.controladores;

import ar.com.MultiOficios.errores.ErrorServicio;
import ar.com.MultiOficios.servicios.UsuarioServicio;
import ar.com.MultiOficios.servicios.ZonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/zona")
public class ZonaControlador {
    
    @Autowired
    private ZonaServicio zonaServicio;
    
     @GetMapping("formZona")
    public String form() {
        return "formZona.html";
    }

    @PostMapping("formZona")
    public String crearZona(@RequestParam int codigoPostal, @RequestParam String ciudad,
            @RequestParam String calle, @RequestParam int numero, 
            @RequestParam String provincia, RedirectAttributes attr) throws ErrorServicio {
                
        try {
            
            zonaServicio.crearZona(codigoPostal, ciudad, calle, numero, provincia);
            
            attr.addFlashAttribute("exito", "se registro la zona correctamente");
        } catch (Exception e) {
            attr.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/zona/formZona";
    }

    @GetMapping("perfil")
    public String perfil() {
        return "perfil.html";
    }
}
