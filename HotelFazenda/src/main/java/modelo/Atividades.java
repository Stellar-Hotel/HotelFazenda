package modelo;

import java.time.LocalDate;
import java.util.Date;

public class Atividades {
    private int IdAtividade, RestricaoIdade,FuncionarioId;
    private String Horario, HorarioFim, NomeAtividade;
    private Date Data;
    private Funcionarios funcionario_Id;
    
    public int getRestricaoIdade() {
		return RestricaoIdade;
	}
	public void setRestricaoIdade(int restricaoIdade) {
		RestricaoIdade = restricaoIdade;
	}
	
    
	public int getIdAtividade() {
		return IdAtividade;
	}
	public void setIdAtividade(int idAtividade) {
		IdAtividade = idAtividade;
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
	public Date getData() {
		return Data;
	}
	public void setData(Date data) {
		Data = data;
	}


   


	
}
