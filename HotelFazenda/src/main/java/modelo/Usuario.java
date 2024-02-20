package modelo;

import java.util.Random;

public class Usuario {

	private int Id_Usuario;
	private String Senha;
	private int Nivel_De_Acesso;
	private String Login;
	Random gerador=new Random();
	
	public void GerarID()
	{
		int id = gerador.nextInt(9999);
		Id_Usuario=id;
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
