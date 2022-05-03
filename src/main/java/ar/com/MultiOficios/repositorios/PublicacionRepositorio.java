
package ar.com.MultiOficios.repositorios;

import ar.com.MultiOficios.entidades.Publicacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, String>{
       
    @Query("SELECT u FROM Publicacion u WHERE u.nombre LIKE %:consulta% OR u.descripcion LIKE %:consulta%")
    public List<Publicacion> buscarPorNombrePordescripcion(@Param("consulta") String consulta);
 
}
