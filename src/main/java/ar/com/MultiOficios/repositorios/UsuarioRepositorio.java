package ar.com.MultiOficios.repositorios;

import ar.com.MultiOficios.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    
    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE :nombre")
    public Usuario buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT u FROM Usuario u")
    public Usuario ListarUsuario();
}
