package com.delduck.usuario.business;

import com.delduck.usuario.business.converter.UsuarioConverter;
import com.delduck.usuario.business.dto.UsuarioDTO;
import com.delduck.usuario.infrastructure.entity.Usuario;
import com.delduck.usuario.infrastructure.exceptions.ConflictException;
import com.delduck.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.delduck.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado " + email));
    }

    public void deletarUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    //apenas chamar nosso metodo na repository
    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    //verificar se o email existe, caso exista, lança exception
    public void emailExiste(String email) {
        try{
            if(verificaEmailExistente(email)) {
                throw new ConflictException("Email já cadastrado " + email);
            }
        }catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado ",  e.getCause());
        }
    }

}
