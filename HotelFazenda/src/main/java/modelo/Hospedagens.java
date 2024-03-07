package modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Hospedagens {

	private int IdHospedagem;
	private Date Checkin;
	private Date Checkout;
	private Float PrecoTotal;
	private Hospedes Hospede;

	public int getHospedagensId() {
		return IdHospedagem;
	}

	public void setHospedagensId(int hospedagensid) {
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
	
	public void setHospedeId(Hospedes Hospede)
	{
		this.Hospede= Hospede;
	}

	public Hospedes getIdHospede() {
		return Hospede;
	}

	public Float getPrecoTotal() {
		return PrecoTotal;
	}

	public void setPrecoTotal(Float precoTotal) {
		PrecoTotal = precoTotal;
	}
}
