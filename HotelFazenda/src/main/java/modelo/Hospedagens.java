package modelo;

import java.time.LocalDate;

public class Hospedagens {
	
	private int Hospedagensid,HospedeId;
	private LocalDate Checkin, Checkout;
	Float PrecoTotal;
	
	

	private Hospede hospede;


	public int getHospedagensid() {
		return Hospedagensid;
	}


	public void setHospedagensid(int hospedagensid) {
		Hospedagensid = hospedagensid;
	}


	public int getHospedeId() {
		return HospedeId;
	}


	public void setHospedeId(int hospedeId) {
		HospedeId = hospedeId;
	}


	public LocalDate getCheckin() {
		return Checkin;
	}


	public void setCheckin(LocalDate checkin) {
		Checkin = checkin;
	}


	public LocalDate getCheckout() {
		return Checkout;
	}


	public void setCheckout(LocalDate checkout) {
		Checkout = checkout;
	}


	public Hospede getHospede() {
		return hospede;
	}


	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	

}
