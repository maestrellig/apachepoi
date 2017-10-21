package it.maestrelli.export.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Dipendente {

	private String codAziendaUfficiale;
	
	
	private String codDipendenteUfficiale;
	
	
	private List<Movimento> movimenti;
	

	private List<Voce> vociRetributive;

	public String getCodAziendaUfficiale() {
		return codAziendaUfficiale;
	}

	@XmlAttribute(name = "CodAziendaUfficiale")
	public void setCodAziendaUfficiale(String codAziendaUfficiale) {
		this.codAziendaUfficiale = codAziendaUfficiale;
	}

	@XmlAttribute(name = "CodDipendenteUfficiale")
	public String getCodDipendenteUfficiale() {
		return codDipendenteUfficiale;
	}

	public void setCodDipendenteUfficiale(String codDipendenteUfficiale) {
		this.codDipendenteUfficiale = codDipendenteUfficiale;
	}

	@XmlElement(name = "Movimento")
	@XmlElementWrapper(name = "Movimenti")
	public List<Movimento> getMovimenti() {
		return movimenti;
	}

	public void setMovimenti(List<Movimento> movimenti) {
		this.movimenti = movimenti;
	}

	@XmlElement(name = "Voce")
	@XmlElementWrapper(name = "VociRetributive")
	public List<Voce> getVociRetributive() {
		return vociRetributive;
	}

	public void setVociRetributive(List<Voce> vociRetributive) {
		this.vociRetributive = vociRetributive;
	}
	
}
