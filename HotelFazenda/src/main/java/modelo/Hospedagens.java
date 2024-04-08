package modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Hospedagens {

	private int IdHospedagem;
	private Date Checkin;
	private Date Checkout;
	private Hospedes Hospede;
	private Quartos Quarto;
	private Float PrecoTotal;

	public Float getPrecoTotal() {
		return PrecoTotal;
	}

	public void setPrecoTotal(Float precoTotal) {
		PrecoTotal = precoTotal;
	}

	public int getIdHospedagem() {
		return IdHospedagem;
	}

	public void setIdHospedagem(int hospedagensid) {
		IdHospedagem = hospedagensid;
	}

	public Date getCheckin() {
		return Checkin;
	}

	public void setCheckin(Date checkin) {
		Checkin = checkin;
	}

	public Date getCheckout() {
		return Checkout;
	}

	public void setCheckout(Date checkout) {
		Checkout = checkout;
	}

	public void setHospde(Hospedes Hospede) {
		this.Hospede = Hospede;
	}

	public Hospedes getHospede() {
		return Hospede;
	}

	public Quartos getQuarto() {
		return Quarto;
	}

	public void setQuarto(Quartos quarto) {
		Quarto = quarto;
	}

}
