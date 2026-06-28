package com.delduck.usuario.business.converter;

import com.delduck.usuario.business.dto.EnderecoDTO;
import com.delduck.usuario.business.dto.TelefoneDTO;
import com.delduck.usuario.business.dto.UsuarioDTO;
import com.delduck.usuario.infrastructure.entity.Endereco;
import com.delduck.usuario.infrastructure.entity.Telefone;
import com.delduck.usuario.infrastructure.entity.Usuario;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(usuarioDTO.getEnderecos() != null ? paraListaEnderecos(usuarioDTO.getEnderecos()) : null)
                .telefones(usuarioDTO.getTelefones() != null ? paraListaTelefones(usuarioDTO.getTelefones()) : null)
                .build();
    }

    public List<Endereco> paraListaEnderecos(List<EnderecoDTO> enderecoDTOS) {
        return enderecoDTOS.stream()
                .map(this::paraEndereco) // transformar cada endereços em uma lista de endereço
                .toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<Telefone> paraListaTelefones(List<TelefoneDTO> telefoneDTOS) {
        return telefoneDTOS.stream()
                .map(this::paraTelefone) // transformar cada telefone em uma lista de telefone
                .toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    public UsuarioDTO paraUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(usuario.getEnderecos() != null ? paraListaEnderecosDTO(usuario.getEnderecos()) : null)
                .telefones(usuario.getTelefones() != null ? paraListaTelefonesDTO(usuario.getTelefones()) : null)
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecosDTO(List<Endereco> endereco) {
        return endereco.stream()
                .map(this::paraEnderecoDTO) // transformar cada endereços em uma lista de endereço
                .toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario usuarioEntity) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuarioEntity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuarioEntity.getEmail())
                .enderecos(usuarioEntity.getEnderecos())
                .telefones(usuarioEntity.getTelefones())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefonesDTO(List<Telefone> telefone) {
        return telefone.stream()
                .map(this::paraTelefoneDTO) // transformar cada telefone em uma lista de telefone
                .toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    public Endereco updateEndereco(EnderecoDTO endDTO, Endereco endEntity) {
        return Endereco.builder()
                .id(endEntity.getId())
                .rua(endDTO.getRua() != null ? endDTO.getRua() : endEntity.getRua())
                .numero(endDTO.getNumero() != null ? endDTO.getNumero() : endEntity.getNumero())
                .complemento(endDTO.getComplemento() != null ? endDTO.getComplemento() : endEntity.getComplemento())
                .cidade(endDTO.getCidade() != null ? endDTO.getCidade() : endEntity.getCidade())
                .estado(endDTO.getEstado() != null ? endDTO.getEstado() : endEntity.getEstado())
                .cep(endDTO.getCep() != null ? endDTO.getCep() : endEntity.getCep())
                .build();
    }

    public Telefone updateTelefone(TelefoneDTO telDTO, Telefone telEntity) {
        return Telefone.builder()
                .id(telEntity.getId())
                .numero(telDTO.getNumero() != null ? telDTO.getNumero() : telEntity.getNumero())
                .ddd(telDTO.getDdd() != null ? telDTO.getDdd() : telEntity.getDdd())
                .build();
    }

    public Endereco paraEnderecoEntity(EnderecoDTO endDTO, Long idUsuario) {
        return Endereco.builder()
                .rua(endDTO.getRua())
                .numero(endDTO.getNumero())
                .complemento(endDTO.getComplemento())
                .cidade(endDTO.getCidade())
                .estado(endDTO.getEstado())
                .cep(endDTO.getCep())
                .usuario_id(idUsuario)
                .build();
    }

    public Telefone paraTelefoneEntity(TelefoneDTO telDTO, Long idUsuario) {
        return Telefone.builder()
                .numero(telDTO.getNumero())
                .ddd(telDTO.getDdd())
                .usuario_id(idUsuario)
                .build();
    }
}
