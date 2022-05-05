
package ar.com.MultiOficios.repositorios;

import ar.com.MultiOficios.entidades.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepositorio extends JpaRepository<Zona, String>{
    
}
