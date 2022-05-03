package ar.com.MultiOficios.controladores;

import ar.com.MultiOficios.entidades.Publicacion;
import ar.com.MultiOficios.servicios.PublicacionServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
}
