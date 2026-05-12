package com.lucca.bff_agendadorTarefas.controller;


import com.lucca.bff_agendadorTarefas.business.dto.EnderecoDTO;
import com.lucca.bff_agendadorTarefas.business.dto.TelefoneDTO;
import com.lucca.bff_agendadorTarefas.business.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService(usuarioDTO);
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                           @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity <Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader("Authorization") String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(@RequestBody UsuarioDTO dto,
                                                           @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizaDadosEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                             @RequestParam("id") Long id,
                                                             @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizaDadosTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                             @RequestParam("id") Long id,
                                                             @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastrarEndereco(token, enderecoDTO));
    }
    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTO> cadastrarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                         @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastrarTelefone(token, telefoneDTO));
    }
}
