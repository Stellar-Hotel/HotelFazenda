package modelo;

public class Usuario {

	private int Id_Usuario;
	private String Senha;
	private int Nivel_De_Acesso;
	private String Login;

	public int getId_Usuario() {
		return Id_Usuario;
	}

	public void setId_Usuario(int id_Usuario) {
		Id_Usuario = id_Usuario;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public int getNivel_De_Acesso() {
		return Nivel_De_Acesso;
	}

	public void setNivel_De_Acesso(int nivel_De_Acesso) {
		Nivel_De_Acesso = nivel_De_Acesso;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

}
