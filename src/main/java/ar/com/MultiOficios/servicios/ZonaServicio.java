package ar.com.MultiOficios.servicios;

import ar.com.MultiOficios.entidades.Zona;
import ar.com.MultiOficios.repositorios.ZonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZonaServicio{
    
    @Autowired
    public ZonaRepositorio zonaRepositorio;
    
    public void crearZona (int codigoPostal , String provincia, int numero, String calle, String municipio, 
            String ciudad ){
    
            Zona zona = new Zona();
            zona.setCodigoPostal(codigoPostal);
            zona.setProvincia(provincia);
            zona.setNumero(numero);
            zona.setMunicipio(municipio);
            zona.setCiudad(ciudad);
            
            zonaRepositorio.save(zona);
    }
}
