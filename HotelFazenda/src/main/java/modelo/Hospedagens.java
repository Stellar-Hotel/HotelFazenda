package modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Hospedagens {
	
	private int Hospedagensid,HospedeId;
	private Date Checkin;
	Date Checkout;
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


	public Hospede getHospede() {
		return hospede;
	}


	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	

}
