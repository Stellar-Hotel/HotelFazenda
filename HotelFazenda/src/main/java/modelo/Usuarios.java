package modelo;

public class Usuarios {

	private int IdUsuario;
	private String Senha;
	private String Login;
	private Boolean Tipo;
	
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
	
	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public Boolean getTipo() {
		return Tipo;
	}

	public void setTipo(Boolean tipo) {
		Tipo = tipo;
	}

}
