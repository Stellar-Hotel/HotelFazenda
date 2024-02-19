package modelo;

public class Funcionarios {
	private int Funcionario_Id, Id_Usuario;
	private String Nome,Sobrenome,Funcao;
	private Float Salario;
	
	public int getFuncionario_Id() {
		return Funcionario_Id;
	}
	public void setFuncionario_Id(int funcionario_Id) {
		Funcionario_Id = funcionario_Id;
	}
	public int getId_Usuario() {
		return Id_Usuario;
	}
	public void setId_Usuario(int id_Usuario) {
		Id_Usuario = id_Usuario;
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

}
