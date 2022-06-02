package ar.com.MultiOficios.repositorios;

import ar.com.MultiOficios.entidades.PublicacionUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionUsuarioRepositorio extends JpaRepository<PublicacionUsuario, String>{
    
}
