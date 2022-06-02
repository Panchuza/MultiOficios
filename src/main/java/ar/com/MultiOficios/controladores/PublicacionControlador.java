package ar.com.MultiOficios.controladores;

import ar.com.MultiOficios.entidades.Publicacion;
import ar.com.MultiOficios.enums.EstadoPublicacion;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class PublicacionControlador {

    @Autowired
    private PublicacionServicio publicacionServicio;

    @GetMapping("/administrarPublicacion")
    public String administrarPublicacion() {
        return "administrarPublicacion.html";
    }

    //*********Levanta las paginas listadoras****************
    @GetMapping("/listarPublicaciones")
    public String listarPublicaciones(ModelMap modelo) throws ErrorServicio {
        List<Publicacion> publicaciones = publicacionServicio.listarPublicaciones();
        modelo.put("publicaciones", publicaciones);
        return "listarPublicaciones.html";
    }

    @PostMapping("/administrarPublicacion")
    public String crearPublicacion(RedirectAttributes attr, @RequestParam(required = false) String nombre, @RequestParam(required = false) String descripcion) throws ErrorServicio {

        try {

            publicacionServicio.crear(nombre, descripcion);
            attr.addFlashAttribute("exito", "La publicacion se cargo exitosamente");

        } catch (ErrorServicio ex) {

            attr.addFlashAttribute("ErrorServicio", ex.getMessage());

        }

        return "redirect:/listarPublicaciones";
    }

    @GetMapping("/modificarPublicacion/{id}")
    public String modificarPublicacion(RedirectAttributes attr, ModelMap model, @PathVariable String id) throws ErrorServicio {
        try {
            Publicacion publicacion = publicacionServicio.buscarPorId(id);
            model.put("publicacion", publicacion);
        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("ErrorServicio", ex.getMessage());
        }
        return "modificarPublicacion.html";
    }

    @PostMapping("/modificarPublicacion")
    public String modificarPublicacion(RedirectAttributes attr, @RequestParam(required = false) String id, @RequestParam(required = false) String nombre, @RequestParam(required = false) String descripcion) throws ErrorServicio {
        try {
            publicacionServicio.modificar(id, nombre, descripcion);
            attr.addFlashAttribute("exito", "La publicacion se modifico exitosamente");
        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("ErrorServicio", ex.getMessage());
        }
        return "redirect:/listarPublicaciones";

    }

    @GetMapping("/DarDeBajaPublicacion/{id}")
    public String DarDeBajaPublicacion(RedirectAttributes attr, ModelMap model, @PathVariable String id) throws ErrorServicio {
        try {
            Publicacion publicacion = publicacionServicio.buscarPorId(id);
            model.put("publicacion", publicacion);
        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("ErrorServicio", ex.getMessage());
        }
        return "DarDeBajaPublicacion.html";
    }

    @PostMapping("/DarDeBajaPublicacion")
    public String DarDeBajaPublicacion(RedirectAttributes attr, @RequestParam(required = false) String id) throws ErrorServicio {
        try {
            publicacionServicio.darDeBajaPublicacion(id);
            attr.addFlashAttribute("warning", "ATENCION!: La publicacion se a dado de baja");
        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("ErrorServicio", ex.getMessage());
        }
        return "redirect:/listarPublicaciones";

    }

    @GetMapping("/eliminarPublicacion/{id}")
    public String eliminarPublicacion(RedirectAttributes attr, ModelMap model, @PathVariable String id) throws ErrorServicio {
        try {
            Publicacion publicacion = publicacionServicio.buscarPorId(id);
            model.put("publicacion", publicacion);
            publicacionServicio.eliminarPublicacion(id);
            attr.addFlashAttribute("warning", "ATENCION!: La publicacion se a eliminado");
        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("ErrorServicio", ex.getMessage());
        }
        return "redirect:/listarPublicaciones";
    }

    
    @GetMapping("/modificarEstado/{id}")
    public String modificarEstado(RedirectAttributes attr, ModelMap model, @PathVariable String id) throws ErrorServicio {
        try {
            publicacionServicio.modificarEstado(id);
        } catch (ErrorServicio ex) {
            attr.addFlashAttribute("ErrorServicio", ex.getMessage());
        }
        return "redirect:/listarPublicaciones";
    }

}
