package modelo;

public class Funcionarios {
	private int FuncionarioId;
	private String Nome,Sobrenome,Funcao;
	private Usuarios Usuario;
	
	public int getFuncionarioId() {
		return FuncionarioId;
	}
	public void setFuncionarioId(int funcionarioId) {
		FuncionarioId = funcionarioId;
	}
	private Float Salario;
	
	
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

}
