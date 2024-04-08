package modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Hospedes {

	private int IdHospede;
	private String Nome, Documento, Sobrenome, Nacionalidade, Pronome, Email;
	private Date DataNasc;
	private Usuarios Usuario;

	public void setCPF(String documento) {
		Documento = documento;
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

	public String getCPF() {
		return Documento;
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
