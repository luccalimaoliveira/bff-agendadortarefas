package com.lucca.bff_agendadorTarefas.business.dto.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTORequest {
    private String email;
    private String senha;

}
