package com.lucca.bff_agendadorTarefas.business;


import com.lucca.bff_agendadorTarefas.business.dto.in.TarefasDTOResquest;
import com.lucca.bff_agendadorTarefas.business.dto.out.TarefasDTOResponse;
import com.lucca.bff_agendadorTarefas.infrastructure.client.TarefasClient;
import com.lucca.bff_agendadorTarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse salvarTarefa (String token, TarefasDTOResquest tarefasDTO){

        return tarefasClient.salvarTarefa(tarefasDTO, token);
    }

    public List<TarefasDTOResponse> buscarTarefaPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
        return tarefasClient.buscarListaDeTarefasPorPeriodo(dataInicial, dataFinal , token);
    }

    public List<TarefasDTOResponse> buscarTarefaPorEmail(String token){

        return tarefasClient.buscarTarefasPorEmail(token);
    }

    public void deletarTarefaPorId(String id, String token) {
        tarefasClient.deletarTarefaPorId(id, token);
    }

    public TarefasDTOResponse alteraStatusTarefa(StatusNotificacaoEnum status, String id, String token){
        return tarefasClient.alteraStatusTarefa(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTOResquest dto, String id, String token){
        return tarefasClient.updateTarefas(dto, id, token);
    }
}
