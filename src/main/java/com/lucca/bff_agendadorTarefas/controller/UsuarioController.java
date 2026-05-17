package com.lucca.bff_agendadorTarefas.controller;


import com.lucca.bff_agendadorTarefas.business.UsuarioService;
import com.lucca.bff_agendadorTarefas.business.dto.EnderecoDTO;
import com.lucca.bff_agendadorTarefas.business.dto.TelefoneDTO;
import com.lucca.bff_agendadorTarefas.business.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar usuário", description = "Cria novo usuário")
    @ApiResponse(responseCode = "200", description = "Salva usuário com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login usuário", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dado do usuário", description = "Busca o dado do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                           @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar usuário por Id", description = "Deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity <Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader("Authorization") String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de usuário", description = "Atualiza dado de usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(@RequestBody UsuarioDTO dto,
                                                           @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço do usuário", description = "Atualiza endereço do usuário")
    @ApiResponse(responseCode = "200", description = "Atualiza endereço com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTO> atualizaDadosEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                             @RequestParam("id") Long id,
                                                             @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone do usuário", description = "Atualiza telefone do usuário")
    @ApiResponse(responseCode = "200", description = "Atualiza telefone com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTO> atualizaDadosTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                             @RequestParam("id") Long id,
                                                             @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salvar endereço de usuário", description = "Salva endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Salva endereço de usuário com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastrarEndereco(token, enderecoDTO));
    }
    @PostMapping("/telefone")
    @Operation(summary = "Salvar telefone de usuário", description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Salva telefone de usuário com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTO> cadastrarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                         @RequestHeader ("Authorization") String token){
        return ResponseEntity.ok(usuarioService.cadastrarTelefone(token, telefoneDTO));
    }
}
