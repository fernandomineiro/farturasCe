package moduloFaturamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EventoContabil {

	@Id
	@Column(name = " CD_EVENTO")
	Short cdEvento;
	@Column(name = " DC_EVENTO")
	String dcEvento;
	@Column(name = " MAINT")
	String maint;

	public Short getCdEvento() {

		return cdEvento;
	}

	public void setCdEvento(Short cdEvento) {

		this.cdEvento = cdEvento;
	}

	public String getDcEvento() {

		return dcEvento;
	}

	public void setDcEvento(String dcEvento) {

		this.dcEvento = dcEvento;
	}

	public String getMaint() {

		return maint;
	}

	public void setMaint(String maint) {

		this.maint = maint;
	}

}
