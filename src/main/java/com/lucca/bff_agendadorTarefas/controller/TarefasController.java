package com.lucca.bff_agendadorTarefas.controller;

import com.lucca.bff_agendadorTarefas.business.dto.in.TarefasDTOResquest;
import com.lucca.bff_agendadorTarefas.infrastructure.security.SecurityConfig;
import com.lucca.bff_agendadorTarefas.business.TarefasService;
import com.lucca.bff_agendadorTarefas.business.dto.out.TarefasDTOResponse;
import com.lucca.bff_agendadorTarefas.infrastructure.enums.StatusNotificacaoEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    public final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar tarefas de usuário", description = "Cria nova tarefa de usuário")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> salvarTarefa(@RequestBody TarefasDTOResquest dto,
                                                           @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.salvarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por período", description = "Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscarListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscarTarefaPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por email do usuário",
            description = "Busca lista de tarefas cadastradas por email do usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscarTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        List<TarefasDTOResponse> tarefas = tarefasService.buscarTarefaPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por Id", description = "Deleta tarefas por Id")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id,
                                                   @RequestHeader(name = "Authorization", required = false) String token) {
        tarefasService.deletarTarefaPorId(id, token);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status de tarefas", description = "Altera status de tarefas")
    @ApiResponse(responseCode = "200", description = "Status alterados  com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusTarefa(@RequestParam("status") StatusNotificacaoEnum status,
                                                                 @RequestParam("id") String id,
                                                                 @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatusTarefa(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas", description = "Altera dados de tarefas")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTOResquest dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }

}
