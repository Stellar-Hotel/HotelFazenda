package modelo;

public class Usuarios {

	private int IdUsuarios;
	private String Senha;
	private int NivelDeAcesso;
	private String Login;
	
	public int getIdUsuarios() {
		return IdUsuarios;
	}
	public void setIdUsuarios(int idUsuario) {
		IdUsuarios = idUsuario;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public int getNivelDeAcesso() {
		return NivelDeAcesso;
	}
	public void setNivelDeAcesso(int nivelDeAcesso) {
		NivelDeAcesso = nivelDeAcesso;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	
	

}
