package com.delduck.usuario.infrastructure.repository;

import com.delduck.usuario.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    boolean existsByEmail(String email);

    // Optional evite o retorno de informações null, evitar 'nullpointerexpection'
    Optional<Usuario> findByEmail(String email);

    @Transactional //nos ajuda a não causar nenhum erro na hora de deletar
    void deleteByEmail(String email);
}
