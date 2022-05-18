package ar.com.MultiOficios.controladores;

import ar.com.MultiOficios.entidades.Publicacion;
import ar.com.MultiOficios.errores.ErrorServicio;
import ar.com.MultiOficios.servicios.PublicacionServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PublicacionControlador {
    
    @Autowired
    private PublicacionServicio publicacionServicio;
    
     @GetMapping("/administrarPublicacion")
    public String administrarPublicacion() {
        return "administrarPublicacion.html";
    }
    
<<<<<<< HEAD
     //*********Levanta las paginas listadoras****************
    @GetMapping("/listarPublicaciones")
=======
     //*********Levanta las pagina listadora****************
     @GetMapping("/listarPublicaciones")
>>>>>>> 9072da7 (contro modificacion)
    public String listarPublicaciones(ModelMap modelo) throws ErrorServicio {
        List<Publicacion> publicaciones = publicacionServicio.listarPublicaciones();
        modelo.put("publicaciones", publicaciones);
        return "listarPublicaciones.html";
    }
    
    
    @PostMapping("/administrarPublicacion")
    public String crearPublicacion(ModelMap modelo, @RequestParam String nombre, @RequestParam String descripcion) throws ErrorServicio{

        try {

            publicacionServicio.crear(nombre,descripcion);
            modelo.put("exito", "La publicacion" + nombre + " Se cargo exitosamente");
            
            return "listarPublicaciones.html";
        } catch (ErrorServicio ex) {
            modelo.put("ErrorServicio", ex.getMessage());
            return "administrarPublicacion.html";
    }
    
     @GetMapping("/modificarPublicacion/{id}")
    public String modificarPublicacion(RedirectAttributes attr, ModelMap model, @PathVariable("id") String id
) throws ErrorServicio {
        try {
            Publicacion publicacion = publicacionServicio.buscarPorId(id);
            model.put("publicacion",publicacion);            
        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("ErrorServicio", ex.getMessage());
        }
            return "modificarPublicacion.html";
    }
    
    @PostMapping("/modificarPublicacion")
    public String modificarPublicacion(RedirectAttributes attr,@RequestParam(required = false) String id, @RequestParam(required = false) String nombre, @RequestParam(required = false) String descripcion) throws ErrorServicio{
        try {
            publicacionServicio.modificar(id,nombre,descripcion);            
        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("ErrorServicio", ex.getMessage());
        }
        return "redirect:/listarPublicaciones";
    }
}
}
