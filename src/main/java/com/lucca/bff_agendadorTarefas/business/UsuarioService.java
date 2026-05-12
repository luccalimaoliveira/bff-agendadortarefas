package com.lucca.bff_agendadorTarefas.business;

import com.lucca.bff_agendadorTarefas.business.dto.UsuarioDTO;
import com.lucca.bff_agendadorTarefas.business.dto.TelefoneDTO;
import com.lucca.bff_agendadorTarefas.business.dto.EnderecoDTO;
import com.lucca.bff_agendadorTarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    public final UsuarioClient usuarioClient;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){
        return usuarioClient.salvarUsuario(usuarioDTO);
    }

    public String loginUsuario(UsuarioDTO usuarioDTO){
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTO buscarUsuarioPorEmail (String email, String token){
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail (String email, String token){
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto){
        return  usuarioClient.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTO atualizarDadosEndereco(Long idEndereco, EnderecoDTO enderecoDTO, String token){
        return usuarioClient.atualizaDadosEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTO atualizaDadosTelefone(Long idTelefone, TelefoneDTO telefoneDTO, String token){
        return usuarioClient.atualizaDadosTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTO cadastrarEndereco(String token, EnderecoDTO enderecoDTO){
        return usuarioClient.cadastrarEndereco(enderecoDTO, token);
    }

    public TelefoneDTO cadastrarTelefone(String token, TelefoneDTO telefoneDTO){
        return  usuarioClient.cadastrarTelefone(telefoneDTO, token);
    }

}
