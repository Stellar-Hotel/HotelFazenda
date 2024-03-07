package modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Hospedagens {

	private int Hospedagensid;
	private Date Checkin;
	private Date Checkout;
	private Float PrecoTotal;
	private Hospedes IdHospede;

	public int getHospedagensId() {
		return Hospedagensid;
	}

	public void setHospedagensId(int hospedagensid) {
		Hospedagensid = hospedagensid;
	}
	public Date getCheckin() {
		return Checkin;
	}

	public void setCheckin(Date checkin2) {
		Checkin = checkin2;
	}

	public Date getCheckout() {
		return Checkout;
	}

	public void setCheckout(Date checkout2) {
		Checkout = checkout2;
	}
	
	public void setHospedeId(Hospedes HospedesId)
	{
		this.IdHospede= HospedesId;
	}

	public Hospedes getIdHospede() {
		return IdHospede;
	}

	public Float getPrecoTotal() {
		return PrecoTotal;
	}

	public void setPrecoTotal(Float precoTotal) {
		PrecoTotal = precoTotal;
	}
}
