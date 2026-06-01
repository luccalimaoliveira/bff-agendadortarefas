package com.lucca.bff_agendadorTarefas.infrastructure.client;

import com.lucca.bff_agendadorTarefas.business.dto.in.EnderecoDTORequest;
import com.lucca.bff_agendadorTarefas.business.dto.in.LoginDTORequest;
import com.lucca.bff_agendadorTarefas.business.dto.in.TelefoneDTORequest;
import com.lucca.bff_agendadorTarefas.business.dto.in.UsuarioDTORequest;
import com.lucca.bff_agendadorTarefas.business.dto.out.EnderecoDTOResponse;
import com.lucca.bff_agendadorTarefas.business.dto.out.TelefoneDTOResponse;
import com.lucca.bff_agendadorTarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
     String login(@RequestBody LoginDTORequest usuarioDTO);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaDadosEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                              @RequestParam("id") Long id,
                                              @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaDadosTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                              @RequestParam("id") Long id,
                                              @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastrarEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                          @RequestHeader ("Authorization") String token);
    @PostMapping("/telefone")
    TelefoneDTOResponse cadastrarTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                          @RequestHeader ("Authorization") String token);
}