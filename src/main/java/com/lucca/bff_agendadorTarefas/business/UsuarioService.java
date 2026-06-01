package com.lucca.bff_agendadorTarefas.business;

import com.lucca.bff_agendadorTarefas.business.dto.in.EnderecoDTORequest;
import com.lucca.bff_agendadorTarefas.business.dto.in.LoginDTORequest;
import com.lucca.bff_agendadorTarefas.business.dto.in.TelefoneDTORequest;
import com.lucca.bff_agendadorTarefas.business.dto.in.UsuarioDTORequest;
import com.lucca.bff_agendadorTarefas.business.dto.out.UsuarioDTOResponse;
import com.lucca.bff_agendadorTarefas.business.dto.out.TelefoneDTOResponse;
import com.lucca.bff_agendadorTarefas.business.dto.out.EnderecoDTOResponse;
import com.lucca.bff_agendadorTarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    public final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvarUsuario(UsuarioDTORequest usuarioDTO){
        return usuarioClient.salvarUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest loginDTO){
        return usuarioClient.login(loginDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail (String email, String token){
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail (String email, String token){
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest usuarioDTO){
        return  usuarioClient.atualizaDadosUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizarDadosEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){
        return usuarioClient.atualizaDadosEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaDadosTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token){
        return usuarioClient.atualizaDadosTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastrarEndereco(String token, EnderecoDTORequest enderecoDTO){
        return usuarioClient.cadastrarEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse cadastrarTelefone(String token, TelefoneDTORequest telefoneDTO){
        return  usuarioClient.cadastrarTelefone(telefoneDTO, token);
    }

}
