package modelo;

import java.time.LocalDate;

public class Atividades {
    private int IdAtividade, restricao_idade,FuncionarioId;
    private String Horario, HorarioFim, NomeAtividade;
    private LocalDate Data;
    
	public int getIdAtividade() {
		return IdAtividade;
	}
	public void setIdAtividade(int idAtividade) {
		IdAtividade = idAtividade;
	}
	public int getRestricao_idade() {
		return restricao_idade;
	}
	public void setRestricao_idade(int restricao_idade) {
		this.restricao_idade = restricao_idade;
	}
	public int getFuncionarioId() {
		return FuncionarioId;
	}
	public void setFuncionarioId(int funcionarioId) {
		FuncionarioId = funcionarioId;
	}
	public String getHorario() {
		return Horario;
	}
	public void setHorario(String horario) {
		Horario = horario;
	}
	public String getHorarioFim() {
		return HorarioFim;
	}
	public void setHorarioFim(String horarioFim) {
		HorarioFim = horarioFim;
	}
	public String getNomeAtividade() {
		return NomeAtividade;
	}
	public void setNomeAtividade(String nomeAtividade) {
		NomeAtividade = nomeAtividade;
	}
	public LocalDate getData() {
		return Data;
	}
	public void setData(LocalDate data) {
		Data = data;
	}


   


	
}
