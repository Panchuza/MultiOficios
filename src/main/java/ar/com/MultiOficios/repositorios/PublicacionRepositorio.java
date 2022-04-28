
package ar.com.MultiOficios.repositorios;

import ar.com.MultiOficios.entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, String>{
    
    @Query("SELECT u FROM Publicacion u WHERE u.nombre LIKE :nombre")
    public Publicacion buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT u FROM Publicacion u")
    public Publicacion ListarPublicacion();
}
