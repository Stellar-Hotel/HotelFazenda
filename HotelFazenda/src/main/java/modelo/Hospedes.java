package modelo;

import java.sql.Date;

public class Hospedes {

	private int IdHospede;
	private String Nome, Documento, Sobrenome, Nacionalidade, Pronome, Email;
	private Date DataNasc;
	private Usuarios Usuario;

	public void setDocumento(String documento) {
		Documento = documento;
	}

	public String getDocumento() {
		return Documento;
	}

	public int getIdHospede() {
		return IdHospede;
	}

	public void setIdHospede(int hospedeId) {
		IdHospede = hospedeId;
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

	public Usuarios getUsuario() {
		return Usuario;
	}

	public void setUsuario(Usuarios Usuario) {
		this.Usuario = Usuario;
	}

	public void setSobrenome(String sobrenome) {
		Sobrenome = sobrenome;
	}

	public String getNacionalidade() {
		return Nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		Nacionalidade = nacionalidade;
	}

	public String getPronome() {
		return Pronome;
	}

	public void setPronome(String pronome) {
		Pronome = pronome;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Date getDataNasc() {
		return DataNasc;
	}

	public void setDataNasc(Date datanasc) {
		DataNasc = datanasc;
	}

}
