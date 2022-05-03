package ar.com.MultiOficios.controladores;

import ar.com.MultiOficios.enums.RolUsuario;
import ar.com.MultiOficios.servicios.UsuarioServicio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

//    @Autowired
//    private RolUsuario rolUsuario;
    @GetMapping("/usuario/form")
    public String form() {
        return "form.html";
    }

    @PostMapping("/usuario/form")
    public String crearUsuario(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String email, @RequestParam String password,
            @RequestParam String confirmarPassword, @RequestParam RolUsuario rolUsuario) {

        try {

            usuarioServicio.crearUsuario(nombre, apellido, email, password, confirmarPassword, rolUsuario);

            modelo.put("exito", "se registro el usuario'" + nombre + "' correctamente");
        } catch (Exception e) {

        }
        return "index.html";
    }
}
