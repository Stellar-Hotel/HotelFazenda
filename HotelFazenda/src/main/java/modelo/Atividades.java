package modelo;

import java.sql.Date;

public class Atividades {
	private int IdAtividade, IdadeMinima;
	private String Horario, HorarioFim, NomeAtividade;
	private Date Data;
	private Funcionarios Funcionario;

	public int getRestricaoIdade() {
		return IdadeMinima;
	}

	public void setRestricaoIdade(int Idade) {
		IdadeMinima = Idade;
	}

	public int getIdAtividade() {
		return IdAtividade;
	}

	public void setIdAtividade(int idAtividade) {
		IdAtividade = idAtividade;
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

	public Funcionarios getFuncionario() {
		return Funcionario;
	}

	public void setFuncionario(Funcionarios idFuncionario) {
		Funcionario = idFuncionario;
	}
}
