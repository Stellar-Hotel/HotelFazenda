package modelo;

import java.time.LocalDate;

public class Hospedagens {
	
	private int Hospedagens_id;
	private LocalDate checkin, checkout;
	public int getHospedagens_id() {
		return Hospedagens_id;
	}
	public void setHospedagens_id(int hospedagens_id) {
		Hospedagens_id = hospedagens_id;
	}
	public LocalDate getCheckin() {
		return checkin;
	}
	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}
	public LocalDate getCheckout() {
		return checkout;
	}
	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}
	public Hospede getHospede() {
		return hospede;
	}
	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	private Hospede hospede;
	

}
