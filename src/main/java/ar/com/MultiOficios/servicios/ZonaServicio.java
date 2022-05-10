package ar.com.MultiOficios.servicios;

import ar.com.MultiOficios.entidades.Zona;
import ar.com.MultiOficios.repositorios.ZonaRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZonaServicio {

    @Autowired
    public ZonaRepositorio zonaRepositorio;

    public void crearZona(int codigoPostal, String ciudad,
            String calle, int numero) throws Exception {

        validarDatos(codigoPostal, ciudad, calle, numero);
        Zona zona = new Zona();
        zona.setCodigoPostal(codigoPostal);
        zona.setCiudad(ciudad);
        zona.setCalle(calle);
        zona.setNumero(numero);

        zonaRepositorio.save(zona);
    }

////-----------------------------------------LISTAR PROVINCIAS--------------------------------------------------

//-----------------------------------------LISTAR PROVINCIAS--------------------------------------------------
//-----------------------------------------VALIDAR LOS DATOS--------------------------------------------------

    public void validarDatos(int codigoPostal, String ciudad,
            String calle, int numero) throws Exception {

        if (codigoPostal == 0 || codigoPostal < 9999) {
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
    }
//-----------------------------------------VALIDAR LOS DATOS--------------------------------------------------

}
