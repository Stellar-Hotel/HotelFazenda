package modelo;

public class Quartos {
	private int IdQuarto, MaxPessoas;
	private String TipoCama, Manutencao,TipoQuarto;
	private Boolean Frigobar, ArCondicionado, Banheira, TV;
	private Float PrecoDiaria;

	public String getTipoQuarto() {
		return TipoQuarto;
	}

	public void setTipoQuarto(String tipoQuarto) {
		TipoQuarto = tipoQuarto;
	}

	public int getIdQuarto() {
		return IdQuarto;
	}

	public void setIdQuarto(int idQuartos) {
		IdQuarto = idQuartos;
	}

	public String getTipoCama() {
		return TipoCama;
	}

	public void setTipoCama(String tipoCama) {
		TipoCama = tipoCama;
	}

	public String getManutencao() {
		return Manutencao;
	}

	public void setManutencao(String manutencao) {
		Manutencao = manutencao;
	}

	public int getMaxPessoas() {
		return MaxPessoas;
	}

	public void setMaxPessoas(int maxPessoas) {
		MaxPessoas = maxPessoas;
	}

	public Boolean getFrigobar() {
		return Frigobar;
	}

	public void setFrigobar(Boolean frigobar) {
		Frigobar = frigobar;
	}

	public Boolean getArCondicionado() {
		return ArCondicionado;
	}

	public void setArCondicionado(Boolean arCondicionado) {
		ArCondicionado = arCondicionado;
	}

	public Boolean getBanheira() {
		return Banheira;
	}

	public void setBanheira(Boolean banheira) {
		Banheira = banheira;
	}

	public Boolean getTV() {
		return TV;
	}

	public void setTV(Boolean tV) {
		TV = tV;
	}

	public Float getPrecoDiaria() {
		return PrecoDiaria;
	}

	public void setPrecoDiaria(Float precoDiaria) {
		PrecoDiaria = precoDiaria;
	}

}
