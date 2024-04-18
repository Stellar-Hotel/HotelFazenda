package modelo;

public class Funcionarios {
	private int IdFuncionario, NivelDeAcesso;
	private String Nome, Sobrenome, Funcao, CPF;
	private Usuarios Usuario;
	private Float Salario;
	
	public int getIdFuncionario() {
		return IdFuncionario;
	}

	public void setIdFuncionario(int funcionarioId) {
		IdFuncionario = funcionarioId;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getSobrenome() {
		return Sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		Sobrenome = sobrenome;
	}

	public String getFuncao() {
		return Funcao;
	}

	public void setFuncao(String funcao) {
		Funcao = funcao;
	}

	public Float getSalario() {
		return Salario;
	}

	public void setSalario(Float salario) {
		Salario = salario;
	}

	public Usuarios getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuarios idUsuario) {
		this.Usuario = idUsuario;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public int getNivelDeAcesso() {
		return NivelDeAcesso;
	}

	public void setNivelDeAcesso(int nivelDeAcesso) {
		NivelDeAcesso = nivelDeAcesso;
	}

}
