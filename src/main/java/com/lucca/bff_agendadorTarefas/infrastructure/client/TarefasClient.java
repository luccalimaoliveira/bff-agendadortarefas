package com.lucca.bff_agendadorTarefas.infrastructure.client;


import com.lucca.bff_agendadorTarefas.business.dto.in.TarefasDTOResquest;
import com.lucca.bff_agendadorTarefas.business.dto.out.TarefasDTOResponse;
import com.lucca.bff_agendadorTarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse salvarTarefa(@RequestBody TarefasDTOResquest tarefasDTO,
                                    @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscarListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> buscarTarefasPorEmail(@RequestHeader("Authorization") String token);


    @DeleteMapping
    void deletarTarefaPorId(@RequestParam("id") String id,
                            @RequestParam("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alteraStatusTarefa(@RequestParam("status") StatusNotificacaoEnum status,
                                          @RequestParam("id") String id,
                                          @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse updateTarefas(@RequestBody TarefasDTOResquest dto,
                                     @RequestParam("id") String id,
                                     @RequestHeader("Authorization") String token);
}