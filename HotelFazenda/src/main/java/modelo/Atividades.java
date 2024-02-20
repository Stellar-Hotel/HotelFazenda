package modelo;

import java.time.LocalDate;

public class Atividades {
    private int Id_Atividade, restricao_idade,Funcionario_Id;
    private String horario, horario_fim, nome_atividade;
    private LocalDate data;


    public int getRestricao_idade() {
		return restricao_idade;
	}


	public void setRestricao_idade(int restricao_idade) {
		this.restricao_idade = restricao_idade;
	}


	public String getHorario() {
		return horario;
	}


	public void setHorario(String horario) {
		this.horario = horario;
	}


	public String getHorario_fim() {
		return horario_fim;
	}


	public void setHorario_fim(String horario_fim) {
		this.horario_fim = horario_fim;
	}


	public String getNome_atividade() {
		return nome_atividade;
	}


	public void setNome_atividade(String nome_atividade) {
		this.nome_atividade = nome_atividade;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public void setId_Atividade(int id_Atividade) {
		Id_Atividade = id_Atividade;
	}


	public int getId_Atividade() {
        return Id_Atividade;
    }
}
