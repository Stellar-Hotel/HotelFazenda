package modelo;

import java.time.LocalDate;

public class Hospede {
	
	private int Hospede_id;
	private String nome, sobrenome, nacionalidade, pronome, email;
	private LocalDate data_nasc;
	public int getHospede_id() {
		return Hospede_id;
	}
	public void setHospede_id(int hospede_id) {
		Hospede_id = hospede_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getPronome() {
		return pronome;
	}
	public void setPronome(String pronome) {
		this.pronome = pronome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getData_nasc() {
		return data_nasc;
	}
	public void setData_nasc(LocalDate data_nasc) {
		this.data_nasc = data_nasc;
	}
	

}
