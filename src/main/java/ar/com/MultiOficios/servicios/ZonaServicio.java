package ar.com.MultiOficios.servicios;

import ar.com.MultiOficios.entidades.Zona;
import ar.com.MultiOficios.errores.ErrorServicio;
import ar.com.MultiOficios.repositorios.ZonaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZonaServicio {

    @Autowired
    public ZonaRepositorio zonaRepositorio;

    public Zona crearZona(String codigoPostal, String ciudad,
            String calle, String numero, String provincia) throws Exception {

//        validarDatos(codigoPostal, ciudad, calle, numero, provincia);
        Zona zona = new Zona();
        zona.setCodigoPostal("");
        zona.setCiudad("");
        zona.setCalle("");
        zona.setNumero("");
        zona.setProvincia("");

        return zonaRepositorio.save(zona);
    }

////-----------------------------------------LISTAR PROVINCIAS--------------------------------------------------
    @Transactional(readOnly = true)
    public Zona buscarPorId(String id) throws ErrorServicio{
        Optional<Zona> respuesta = zonaRepositorio.findById(id);
        if(respuesta.isPresent()){
            return respuesta.get();
        } else {
            throw new ErrorServicio("No se encontro el usuario");
        }
    }
//-----------------------------------------LISTAR PROVINCIAS--------------------------------------------------
//-----------------------------------------VALIDAR LOS DATOS--------------------------------------------------

    public void validarDatos(int codigoPostal, String ciudad,
            String calle, int numero, String provincia) throws Exception {

        if (codigoPostal == 0 || codigoPostal > 9999) {
            throw new Exception("Error: El codigo postal del Usuario no puede ser nulo");
        }
        if (ciudad == null || ciudad.isEmpty()) {
            throw new Exception("Error: La ciudad del Usuario no puede ser nula");
        }
        if (calle == null || calle.isEmpty()) {
            throw new Exception("Error: La calle del Usuario no puede ser nula");
        }
        if (numero == 0 || numero > 99999) {
            throw new Exception("Error: La calle del Usuario no puede ser nula");
        }
        if (provincia == null || provincia.isEmpty()) {
            throw new Exception("Error: La provincia del Usuario no puede ser nula");
        }
    }
//-----------------------------------------VALIDAR LOS DATOS--------------------------------------------------

}
