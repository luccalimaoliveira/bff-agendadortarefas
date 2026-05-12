package com.lucca.bff_agendadorTarefas.infrastructure.client;

import com.lucca.bff_agendadorTarefas.business.dto.EnderecoDTO;
import com.lucca.bff_agendadorTarefas.business.dto.TelefoneDTO;
import com.lucca.bff_agendadorTarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTO salvarUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("/login")
     String login(@RequestBody UsuarioDTO usuarioDTO);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
   UsuarioDTO atualizaDadosUsuario(@RequestBody UsuarioDTO dto,
                                   @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTO atualizaDadosEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                      @RequestParam("id") Long id,
                                      @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTO atualizaDadosTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                      @RequestParam("id") Long id,
                                      @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTO cadastrarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                  @RequestHeader ("Authorization") String token);
    @PostMapping("/telefone")
    TelefoneDTO cadastrarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                  @RequestHeader ("Authorization") String token);
}