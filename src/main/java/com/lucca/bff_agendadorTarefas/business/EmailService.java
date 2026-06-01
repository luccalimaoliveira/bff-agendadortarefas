package com.lucca.bff_agendadorTarefas.business;

import com.lucca.bff_agendadorTarefas.business.dto.out.TarefasDTOResponse;
import com.lucca.bff_agendadorTarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    public final EmailClient emailClient;

    public void enviaEmail(TarefasDTOResponse tarefasDTOResponse) {
        emailClient.enviarEmail(tarefasDTOResponse);
    }
}
