package ar.com.MultiOficios.controladores;

import ar.com.MultiOficios.repositorios.UsuarioRepositorio;
import ar.com.MultiOficios.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @GetMapping("/perfil")
    public String perfil() {
        return "perfil.html";
    }

}
