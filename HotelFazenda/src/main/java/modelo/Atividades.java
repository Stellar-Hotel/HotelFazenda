package modelo;

import java.time.LocalDate;

public class Atividades {
    private int Id_Atividade, restricao_idade;
    private String horario, horario_fim, nome_atividade;
    private LocalDate data;


    public int getId_Atividade() {
        return Id_Atividade;
    }
}
