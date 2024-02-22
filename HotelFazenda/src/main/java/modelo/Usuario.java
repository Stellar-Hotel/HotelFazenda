package modelo;

import java.util.Random;

public class Usuario {

	private int IdUsuario;
	private String Senha;
	private int NivelDeAcesso;
	private String Login;
	
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
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
