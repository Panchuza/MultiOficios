
package ar.com.MultiOficios.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SobreNosotrosControlador {
    
    @GetMapping("/sobreNosotros")
    public String sobreNosotros(){
        return "sobrenosotros.html";
    }
    
}
